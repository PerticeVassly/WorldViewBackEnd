package org.interaction.interactionbackend.controller;

import org.interaction.interactionbackend.enums.PhotoTheme;
import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.serviceimpl.PhotoServiceImpl;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoServiceImpl photoServiceImpl;

    @PostMapping("/add")
    public ResponseVO addPhoto(@RequestBody Map<String,String> body, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        String url = body.get("url");
        String description = body.get("description");
        PhotoTheme theme = PhotoTheme.valueOf(body.get("theme"));
        return photoServiceImpl.addPhoto(currentUser, url, description, theme);
    }

    @PostMapping("/delete")
    public ResponseVO deletePhoto(@RequestBody Map<String,String> body, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        String url = body.get("url");
        return photoServiceImpl.deletePhoto(currentUser, url);
    }

    @PostMapping("/getAllByEmail")
    public ResponseVO getAllByEmail(@RequestParam("email") String email) {
        return photoServiceImpl.getAllByEmail(email);
    }

    @PostMapping("/getAllByTheme")
    public ResponseVO getAllByTheme(@RequestParam("theme") PhotoTheme theme) {
        return photoServiceImpl.getAllByTheme(theme);
    }
}
