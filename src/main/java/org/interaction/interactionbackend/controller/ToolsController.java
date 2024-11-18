package org.interaction.interactionbackend.controller;


import org.interaction.interactionbackend.serviceimpl.ImageServiceImpl;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ToolsController {
    @Autowired
    ImageServiceImpl imageServiceImpl;

    @PostMapping("/images")
    public ResponseVO upload(@RequestParam MultipartFile file){
        return imageServiceImpl.upload(file);
    }

}
