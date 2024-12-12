package org.interaction.interactionbackend.controller;

import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.serviceimpl.CommunityServiceImpl;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/service") //todo() changed to the "community" path
public class CommunityController {

    @Autowired
    private CommunityServiceImpl communityServiceImpl;

    @PostMapping("/register")
    public ResponseVO register(HttpServletRequest request, @RequestBody HashMap<String, String> info) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return communityServiceImpl.register(currentUser, info.get("contact"), info.get("description"), info.get("photo"));
    }

    @PostMapping("/getAll") // get ALL the photographer's info
    public ResponseVO getAll() {
        return communityServiceImpl.getAll();
    }

    @PostMapping("/getCollection/{email}")
    public ResponseVO getCollection(@PathVariable("email") String email) {
        return communityServiceImpl.getCollection(email);
    }

    @PostMapping("/collect/{email}")
    public ResponseVO collect(HttpServletRequest request, @PathVariable("email") String email) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return communityServiceImpl.collect(currentUser, email);
    }

    @PostMapping("/cancelCollect/{email}")
    public ResponseVO cancelCollect(HttpServletRequest request, @PathVariable("email") String email) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return communityServiceImpl.cancelCollect(currentUser, email);
    }

    @PostMapping("/hasCollected/{email}")
    public ResponseVO hasCollected(HttpServletRequest request, @PathVariable("email") String email) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return communityServiceImpl.hasCollected(currentUser, email);
    }

    @PostMapping("/favor/{email}")
    public ResponseVO favorite(HttpServletRequest request, @PathVariable("email") String email) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return communityServiceImpl.favorite(currentUser, email);
    }

    @PostMapping("/cancelFavor/{email}")
    public ResponseVO cancelFavorite(HttpServletRequest request, @PathVariable("email") String email) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return communityServiceImpl.cancelFavorite(currentUser, email);
    }

    @PostMapping("/hasFavored/{email}")
    public ResponseVO hasFavorited(HttpServletRequest request, @PathVariable("email") String email) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return communityServiceImpl.hasFavorited(currentUser, email);
    }

    @PostMapping("/getFans/{email}")
    public ResponseVO getFans(@PathVariable("email") String email) {
        return communityServiceImpl.getFans(email);
    }

    @PostMapping("/upload") // 上传到个人相册
    public ResponseVO upload(HttpServletRequest request, @RequestBody List<HashMap<String, String>> info) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return communityServiceImpl.upload(currentUser, info);
    }

    @PostMapping("/getAllPhotos/{email}")
    public ResponseVO getAllPhotos(@PathVariable("email") String email) {
        return communityServiceImpl.getAllPhotos(email);
    }

    @PostMapping("/hasJoined")
    public ResponseVO hasJoined(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return communityServiceImpl.hasJoined(currentUser);
    }

    @PostMapping("/getFavors/{email}") // 获取email用户被点赞数
    public ResponseVO getFavors(@PathVariable("email") String email) {
        return communityServiceImpl.getFavoredNum(email);
    }


}
