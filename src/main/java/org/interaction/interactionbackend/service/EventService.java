package org.interaction.interactionbackend.service;

import org.interaction.interactionbackend.po.Participant;
import org.interaction.interactionbackend.repository.ParticipantRepository;
import org.interaction.interactionbackend.util.ResponseBuilder;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private ParticipantRepository participantRepository;

    public ResponseVO registerEvent(Integer userId, String contact,
                                    Integer eventId) {
        // check if it has already registered
        Participant participant =
                participantRepository.findByUserIdAndAndEventId(userId,
                        eventId);
        if (participant != null) {
            return ResponseBuilder.buildErrorResponse("你已经报名过此活动", null);
        }
        // save new participant
        Participant newParticipant = new Participant(userId, contact,
                eventId);
        participantRepository.save(newParticipant);
        return ResponseBuilder.buildSuccessResponse("报名活动成功", null);
    }
}
