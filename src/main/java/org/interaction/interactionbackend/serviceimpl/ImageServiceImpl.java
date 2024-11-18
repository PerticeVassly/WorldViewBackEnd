package org.interaction.interactionbackend.serviceimpl;

import org.interaction.interactionbackend.util.OssUtil;
import org.interaction.interactionbackend.util.ResponseBuilder;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl {

    @Autowired
    private OssUtil ossUtil;

    public ResponseVO upload(MultipartFile file) {
        try {
            String url = ossUtil.upload(file.getOriginalFilename(), file.getInputStream());
            return ResponseBuilder.buildSuccessResponse("上传成功", url);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseBuilder.buildErrorResponse("上传失败", null);
        }
    }

}
