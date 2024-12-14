package org.interaction.interactionbackend.serviceimpl;

import org.interaction.interactionbackend.enums.PhotoTheme;
import org.interaction.interactionbackend.exception.WorldViewException;
import org.interaction.interactionbackend.po.FavorPhoto;
import org.interaction.interactionbackend.po.Photo;
import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.repository.FavorPhotoRepository;
import org.interaction.interactionbackend.repository.PhotoRepository;
import org.interaction.interactionbackend.repository.UserRepository;
import org.interaction.interactionbackend.util.ResponseBuilder;
import org.interaction.interactionbackend.vo.PhotoVO;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavorPhotoRepository favorPhotoRepository;

    public ResponseVO addPhoto(User currentUser, String title, String url, String description, PhotoTheme theme) {
        Photo photo = new Photo();
        photo.setUserId(currentUser.getId());
        photo.setUrl(url);
        photo.setDescription(description);
        photo.setTheme(theme);
        photo.setTitle(title);
        if (photoRepository.findByUrl(url).isPresent()) {
            throw WorldViewException.photoExist();
        }
        photoRepository.save(photo);
        return ResponseBuilder.buildSuccessResponse("上传成功", null);
    }

    public ResponseVO deletePhoto(User currentUser, String url) {
        Photo photo = photoRepository.findByUrl(url).orElseThrow(WorldViewException::photoExist);
        if (!photo.getUserId().equals(currentUser.getId())) {
            throw WorldViewException.permissionDeny();
        }
        photoRepository.delete(photo);
        return ResponseBuilder.buildSuccessResponse("删除成功", null);
    }

    public ResponseVO fetchPhotosByEmail(String email, int page, int limits) {
        PageRequest pageRequest = PageRequest.of(page, limits);
        User user = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound);
        List<PhotoVO> photoVOList = photoRepository.findAllByUserId(user.getId(), pageRequest).stream()
                .map(photo -> new PhotoVO(photo, user))
                .collect(Collectors.toList());
        return ResponseBuilder.buildSuccessResponse("获取成功", photoVOList);
    }

//    public ResponseVO fetchPhotosByTheme(PhotoTheme theme, int page, int limits) {
//        PageRequest pageRequest = PageRequest.of(page, limits);
//        List<PhotoVO> photoVOList = photoRepository.findByTheme(theme, pageRequest).stream()
//                .map(photo -> {
//                    User user = userRepository.findById(photo.getUserId()).orElseThrow(WorldViewException::userNotFound);
//                    return new PhotoVO(photo, user);
//                })
//                .collect(Collectors.toList());
//        return ResponseBuilder.buildSuccessResponse("获取成功", photoVOList);
//    }

    public ResponseVO fetchPhotos(int page, int limit, String key) {
        // return the (page, limits) photos
        List<Photo> photoList;
        if (key == null || key.equals("default")) {
            photoList = photoRepository.findAll(PageRequest.of(page, limit)).getContent();
        } else if (key.equals("new")) {
            // 所有带有newTag的photo
            photoList = photoRepository.findAllByNewTag(true, PageRequest.of(page, limit));
        } else if (key.equals("recommend")) {
            // 所有带有recommendTag的photo
            photoList = photoRepository.findAllByRecommendTag(true, PageRequest.of(page, limit));
        } else if (key.equals("ranking")) {
            // 所有带有rankingTag的photo
            photoList = photoRepository.findAllByRankingTag(true, PageRequest.of(page, limit));
        } else {
            throw WorldViewException.invalidKey();
        }
        List<PhotoVO> photoVOList = photoList.stream()
                .map(photo -> {
                    User user = userRepository.findById(photo.getUserId()).orElseThrow(WorldViewException::userNotFound);
                    return new PhotoVO(photo, user);
                })
                .collect(Collectors.toList());
        return ResponseBuilder.buildSuccessResponse("获取成功", photoVOList);
    }

    public ResponseVO favorPhoto(User currentUser, String url) {
        Photo photo = photoRepository.findByUrl(url).orElseThrow(WorldViewException::photoNotFound);
        Integer userId = currentUser.getId();
        Integer favoredId = photo.getUserId();
        if (userId.equals(favoredId)) {
            throw WorldViewException.selfOperationNotAllowed();
        }
        if (favorPhotoRepository.findByUserIdAndUrl(userId, url).isPresent()) {
            throw WorldViewException.hasOperated();
        }
        favorPhotoRepository.save(new FavorPhoto(userId, url));

        photo.setLikes(photo.getLikes() + 1);
        photoRepository.save(photo);

        return ResponseBuilder.buildSuccessResponse("点赞成功", null);
    }

    public ResponseVO cancelFavorPhoto(User currentUser, String url) {
        Photo photo = photoRepository.findByUrl(url).orElseThrow(WorldViewException::photoNotFound);
        Integer userId = currentUser.getId();
        Integer favoredId = photo.getUserId();
        if (userId.equals(favoredId)) {
            throw WorldViewException.selfOperationNotAllowed();
        }
        FavorPhoto favorPhoto = favorPhotoRepository.findByUserIdAndUrl(userId, url).orElseThrow(WorldViewException::hasOperated);
        favorPhotoRepository.delete(favorPhoto);

        photo.setLikes(Math.max(photo.getLikes() - 1, 0));
        photoRepository.save(photo);

        return ResponseBuilder.buildSuccessResponse("取消点赞成功", null);
    }

    public ResponseVO hasFavorPhoto(User currentUser, String url) {
        Integer userId = currentUser.getId();
        if (favorPhotoRepository.findByUserIdAndUrl(userId, url).isPresent()) {
            return ResponseBuilder.buildSuccessResponse("已点赞", true);
        } else {
            return ResponseBuilder.buildSuccessResponse("未点赞", false);
        }
    }

    public ResponseVO getFavoringPhotos(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound);
        List<FavorPhoto> favorPhotoList = favorPhotoRepository.findAllByUserId(user.getId());
        List<PhotoVO> photoVOList = favorPhotoList.stream()
                .map(favorPhoto -> {
                    Photo photo = photoRepository.findByUrl(favorPhoto.getUrl()).orElseThrow(WorldViewException::photoNotFound);
                    User favoredUser = userRepository.findById(photo.getUserId()).orElseThrow(WorldViewException::userNotFound);
                    return new PhotoVO(photo, favoredUser);
                })
                .collect(Collectors.toList());
        return ResponseBuilder.buildSuccessResponse("获取成功", photoVOList);
    }

    public ResponseVO getFavoredNum(String url) {
        Photo photo = photoRepository.findByUrl(url).orElseThrow(WorldViewException::photoNotFound);
        int favoredNum = photo.getLikes();
        return ResponseBuilder.buildSuccessResponse("获取成功", favoredNum);
    }
}

