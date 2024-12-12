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
        return photoServiceImpl.addPhoto(currentUser, url, description, null);
    }

    @PostMapping("/delete")
    public ResponseVO deletePhoto(@RequestBody Map<String,String> body, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        String url = body.get("url");
        return photoServiceImpl.deletePhoto(currentUser, url);
    }

    @PostMapping("/fetchPhotosByEmail")
    public ResponseVO fetchPhotosByEmail(@RequestParam("email") String email, @RequestParam("page") int page, @RequestParam("limits") int limit) {
        return photoServiceImpl.fetchPhotosByEmail(email, page, limit);
    }

    @PostMapping("/fetchPhotosByTheme")
    public ResponseVO fetchPhotosByTheme(@RequestParam("theme") PhotoTheme theme, @RequestParam("page") int page, @RequestParam("limits") int limit) {
        return photoServiceImpl.fetchPhotosByTheme(theme, page, limit);
    }

    @PostMapping("/fetchPhotos")
    public ResponseVO fetchPhotos(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return photoServiceImpl.fetchPhotos(page, limit);
    }

    @PostMapping("/favor/{url}")
    public ResponseVO favorPhoto(HttpServletRequest request, @PathVariable("url") String url) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return photoServiceImpl.favorPhoto(currentUser, url);
    }

    @PostMapping("/cancelFavor/{url}")
    public ResponseVO cancelFavorPhoto(HttpServletRequest request, @PathVariable("url") String url) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return photoServiceImpl.cancelFavorPhoto(currentUser, url);
    }

    @PostMapping("/hasFavor/{url}")
    public ResponseVO hasFavorPhoto(HttpServletRequest request, @PathVariable("url") String url) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return photoServiceImpl.hasFavorPhoto(currentUser, url);
    }

    @PostMapping("/getFavoredPhotos/{email}")
    public ResponseVO getFavoredPhotos(@PathVariable("email") String email) {
        return photoServiceImpl.getFavoringPhotos(email);
    }

    @PostMapping("/getFavoredNum")
    public ResponseVO getFavoredNum(@RequestParam("url") String url) {
        return photoServiceImpl.getFavoredNum(url);
    }
}
