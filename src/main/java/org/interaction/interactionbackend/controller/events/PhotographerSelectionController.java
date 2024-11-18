package org.interaction.interactionbackend.controller.events;

import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.serviceimpl.events.PhotographerSelectionServiceImpl;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/event/3")
public class PhotographerSelectionController {

    @Autowired
    private PhotographerSelectionServiceImpl photographerSelectionServiceImpl;

    @PostMapping("/register") //here register also means join the selection
    public ResponseVO registerEvent(@RequestBody Map<String, String> formData, HttpServletRequest request) {
        String contact = formData.get("contact");
        String description = formData.get("description");
        String photo = formData.get("photo");
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return photographerSelectionServiceImpl.joinSelection(currentUser, contact, description, photo);
    }

}
