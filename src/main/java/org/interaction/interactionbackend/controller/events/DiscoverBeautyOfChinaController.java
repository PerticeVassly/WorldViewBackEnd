package org.interaction.interactionbackend.controller.events;

import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.serviceimpl.events.DiscoverBeautyOfChinaServiceImpl;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/event/2")
public class DiscoverBeautyOfChinaController {

    @Autowired
    DiscoverBeautyOfChinaServiceImpl discoverBeautyOfChinaServiceImpl;

    @PostMapping("/register")
    public ResponseVO registerEvent(@RequestBody Map<String, String> formData, HttpServletRequest request) {
        String contact = formData.get("contact");
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return discoverBeautyOfChinaServiceImpl.registerEvent(currentUser, contact);
    }

    @PostMapping("/hasRegistered")
    public ResponseVO hasRegistered(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return discoverBeautyOfChinaServiceImpl.hasRegistered(currentUser);
    }

}
