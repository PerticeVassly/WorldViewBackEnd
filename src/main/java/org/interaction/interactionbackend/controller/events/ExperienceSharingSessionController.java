package org.interaction.interactionbackend.controller.events;

import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.serviceimpl.events.ExperienceSharingSessionServiceImpl;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/event/4")
public class ExperienceSharingSessionController {

    @Autowired
    private ExperienceSharingSessionServiceImpl experienceSharingSessionServiceImpl;

    @PostMapping("/register")
    public ResponseVO registerEvent(@RequestBody Map<String, String> formData, HttpServletRequest request) {
        String contact = formData.get("contact");
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return experienceSharingSessionServiceImpl.registerEvent(currentUser, contact);
    }

    @PostMapping("/hasRegistered")
    public ResponseVO hasRegistered(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return experienceSharingSessionServiceImpl.hasRegistered(currentUser);
    }
}
