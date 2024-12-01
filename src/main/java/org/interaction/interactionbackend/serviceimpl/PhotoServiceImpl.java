package org.interaction.interactionbackend.serviceimpl;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
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

    public ResponseVO getAllByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound);
        List<PhotoVO> photoVOList = photoRepository.findByUserId(user.getId()).stream()
                .map(photo -> new PhotoVO(photo, user))
                .collect(Collectors.toList());
        return ResponseBuilder.buildSuccessResponse("获取成功", photoVOList);
    }

    public ResponseVO getAllByTheme(PhotoTheme theme) {
        List<PhotoVO> photoVOList = photoRepository.findByTheme(theme).stream()
                .map(photo -> {
                    User user = userRepository.findById(photo.getUserId()).orElseThrow(WorldViewException::userNotFound);
                    return new PhotoVO(photo, user);
                })
                .collect(Collectors.toList());
        return ResponseBuilder.buildSuccessResponse("获取成功", photoVOList);
    }
}
