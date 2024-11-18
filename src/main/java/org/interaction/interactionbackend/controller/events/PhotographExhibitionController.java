package org.interaction.interactionbackend.controller.events;

import org.interaction.interactionbackend.po.User;
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
    private PhotographerSelectionServiceImpl photographerSelectionServiceImpl;

    @PostMapping("/register")
    public ResponseVO registerEvent(@RequestBody Map<String, String> formData, HttpServletRequest request) {
        String contact = formData.get("contact");
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return photographerSelectionServiceImpl.registerEvent(currentUser, contact);
    }
}

