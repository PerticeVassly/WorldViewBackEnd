package org.interaction.interactionbackend.controller;

import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.serviceimpl.CommunityServiceImpl;
import org.interaction.interactionbackend.vo.MemberVO;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

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

    @PostMapping("/getCollection")
    public ResponseVO getCollection(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return communityServiceImpl.getCollection(currentUser);
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
}
