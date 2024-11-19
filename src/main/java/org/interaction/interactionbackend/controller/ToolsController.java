package org.interaction.interactionbackend.controller;


import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.serviceimpl.ImageServiceImpl;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/image")
public class ToolsController {
    @Autowired
    ImageServiceImpl imageServiceImpl;

    @PostMapping("/upload")
    public ResponseVO upload(@RequestBody MultipartFile file, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return imageServiceImpl.upload(file, currentUser);
    }

}
