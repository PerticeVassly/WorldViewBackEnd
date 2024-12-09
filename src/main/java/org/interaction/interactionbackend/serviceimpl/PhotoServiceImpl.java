package org.interaction.interactionbackend.serviceimpl;

import org.interaction.interactionbackend.enums.PhotoTheme;
import org.interaction.interactionbackend.exception.WorldViewException;
import org.interaction.interactionbackend.po.Photo;
import org.interaction.interactionbackend.po.User;
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

    public ResponseVO addPhoto(User currentUser, String url, String description, PhotoTheme theme) {
        Photo photo = new Photo();
        photo.setUserId(currentUser.getId());
        photo.setUrl(url);
        photo.setDescription(description);
        photo.setTheme(theme);
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
        List<PhotoVO> photoVOList = photoRepository.findByUserId(user.getId(), pageRequest).stream()
                .map(photo -> new PhotoVO(photo, user))
                .collect(Collectors.toList());
        return ResponseBuilder.buildSuccessResponse("获取成功", photoVOList);
    }

    public ResponseVO fetchPhotosByTheme(PhotoTheme theme, int page, int limits) {
        PageRequest pageRequest = PageRequest.of(page, limits);
        List<PhotoVO> photoVOList = photoRepository.findByTheme(theme, pageRequest).stream()
                .map(photo -> {
                    User user = userRepository.findById(photo.getUserId()).orElseThrow(WorldViewException::userNotFound);
                    return new PhotoVO(photo, user);
                })
                .collect(Collectors.toList());
        return ResponseBuilder.buildSuccessResponse("获取成功", photoVOList);
    }

    public ResponseVO fetchPhotos(int page, int limit) {
        // return the (page, limits) photos
        List<Photo> photoList = photoRepository.findAll(PageRequest.of(page, limit)).getContent();
        List<PhotoVO> photoVOList = photoList.stream()
                .map(photo -> {
                    User user = userRepository.findById(photo.getUserId()).orElseThrow(WorldViewException::userNotFound);
                    return new PhotoVO(photo, user);
                })
                .collect(Collectors.toList());
        return ResponseBuilder.buildSuccessResponse("获取成功", photoVOList);
    }
}

