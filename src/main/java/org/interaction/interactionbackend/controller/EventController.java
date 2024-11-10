package org.interaction.interactionbackend.controller;

import org.interaction.interactionbackend.service.EventService;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    /**
     * register event api: a user sign up for an event
     * @param formData <br>
     * contact: contact // student contact number <br>
     * eventId: eventId // 1-4 mapping different event} <br>
     * @return {code: 0, msg: error_msg} or <br>
     * {code: 1, msg: success_msg}
     */
    @PostMapping("/register")
    public ResponseVO registerEvent(@RequestBody Map<String, String> formData,
                                    HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        ResponseVO ans = eventService.registerEvent(userId, formData.get(
                "contact"), Integer.parseInt(formData.get("eventId")));
        return ans;
    }
}
