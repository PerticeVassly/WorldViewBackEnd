package org.interaction.interactionbackend.controller.events;

import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.serviceimpl.events.PhotographerExhibitionServiceImpl;
import org.interaction.interactionbackend.serviceimpl.events.PhotographerSelectionServiceImpl;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/event/1")
public class PhotographExhibitionController {

    @Autowired
    private PhotographerExhibitionServiceImpl photographerExhibitionServiceImpl;

    @PostMapping("/register")
    public ResponseVO registerEvent(@RequestBody Map<String, String> formData, HttpServletRequest request) {
        String contact = formData.get("contact");
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return photographerExhibitionServiceImpl.registerEvent(currentUser, contact);
    }

    @PostMapping("/hasRegistered")
    public ResponseVO hasRegistered(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return photographerExhibitionServiceImpl.hasRegistered(currentUser);
    }
}

