package org.interaction.interactionbackend.controller;

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
        String title = body.get("title");
        String url = body.get("url");
        String description = body.get("description");
        return photoServiceImpl.addPhoto(currentUser, title, url, description, null);
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

//    @PostMapping("/fetchPhotosByTheme")
//    public ResponseVO fetchPhotosByTheme(@RequestParam("theme") PhotoTheme theme, @RequestParam("page") int page, @RequestParam("limits") int limit) {
//        return photoServiceImpl.fetchPhotosByTheme(theme, page, limit);
//    }

    @PostMapping("/fetchPhotos")
    public ResponseVO fetchPhotos(@RequestParam("page") int page, @RequestParam("limit") int limit, @RequestParam("key") String key) {
        return photoServiceImpl.fetchPhotos(page, limit, key);
    }

    @PostMapping("/favor")
    public ResponseVO favorPhoto(HttpServletRequest request, @RequestParam("url") String url) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return photoServiceImpl.favorPhoto(currentUser, url);
    }

    @PostMapping("/cancelFavor")
    public ResponseVO cancelFavorPhoto(HttpServletRequest request, @RequestParam("url") String url) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return photoServiceImpl.cancelFavorPhoto(currentUser, url);
    }

    @PostMapping("/hasFavor")
    public ResponseVO hasFavorPhoto(HttpServletRequest request, @RequestParam("url") String url) {
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
