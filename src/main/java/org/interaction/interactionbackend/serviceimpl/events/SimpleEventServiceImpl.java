package org.interaction.interactionbackend.serviceimpl.events;

import org.interaction.interactionbackend.exception.WorldViewException;
import org.interaction.interactionbackend.po.Participant;
import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.repository.ParticipantRepository;
import org.interaction.interactionbackend.util.ResponseBuilder;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SimpleEventServiceImpl {

    @Autowired
    private ParticipantRepository participantRepository;

    public ResponseVO registerEvent(User user, String contact) {
        // check if it has already registered
        Integer userId = user.getId();
        Integer eventId = getEventId();
        if (participantRepository.findByUserIdAndEventId(userId, eventId).isPresent()) {
            throw WorldViewException.alreadyRegisteredEvent();
        }
        // save new participant
        Participant newParticipant = new Participant(userId, contact, eventId);
        participantRepository.save(newParticipant);
        return ResponseBuilder.buildSuccessResponse("报名活动成功", null);
    }

    public ResponseVO hasRegistered(User currentUser) {
        Integer userId = currentUser.getId();
        Integer eventId = getEventId();
        if (participantRepository.findByUserIdAndEventId(userId, eventId).isPresent()) {
            return ResponseBuilder.buildSuccessResponse("已报名", null);
        } else {
            return ResponseBuilder.buildErrorResponse("未报名", null);
        }
    }

    public abstract Integer getEventId();
}
