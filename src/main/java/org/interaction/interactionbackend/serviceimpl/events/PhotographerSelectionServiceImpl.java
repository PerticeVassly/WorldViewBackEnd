package org.interaction.interactionbackend.serviceimpl.events;

import org.interaction.interactionbackend.exception.WorldViewException;
import org.interaction.interactionbackend.po.PhotographerCandidate;
import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.repository.PhotographerCandidateRepository;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.interaction.interactionbackend.util.ResponseBuilder;


@Service
public class PhotographerSelectionServiceImpl extends SimpleEventServiceImpl {

    @Autowired
    private PhotographerCandidateRepository photographerCandidateRepository;

    public ResponseVO joinSelection(User user, String contact, String description, String photo) {
        registerEvent(user, contact);
        // save the candidate
        Integer userId = user.getId();
        PhotographerCandidate candidate = photographerCandidateRepository.findByUserId(userId);
        if (candidate != null) {
            throw WorldViewException.alreadyJoinedSelection();
        }
        PhotographerCandidate newCandidate = new PhotographerCandidate(userId, contact, description, photo);
        photographerCandidateRepository.save(newCandidate);
        return ResponseBuilder.buildSuccessResponse("报名活动成功", null);
    }

    @Override
    public Integer getEventId() {
        return 3;
    }

}
