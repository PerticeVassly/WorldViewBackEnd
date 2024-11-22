package org.interaction.interactionbackend.serviceimpl.events;

import org.interaction.interactionbackend.exception.WorldViewException;
import org.interaction.interactionbackend.po.PhotographerCandidate;
import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.repository.PhotographerCandidateRepository;
import org.interaction.interactionbackend.repository.UserRepository;
import org.interaction.interactionbackend.vo.PhotographerCandidateVO;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.interaction.interactionbackend.util.ResponseBuilder;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotographerSelectionServiceImpl extends SimpleEventServiceImpl {

    @Autowired
    private PhotographerCandidateRepository photographerCandidateRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseVO joinSelection(User user, String contact, String description, String photo) {
        registerEvent(user, contact);
        // save the candidate
        Integer userId = user.getId();
        if (photographerCandidateRepository.findByUserId(userId).isPresent()) {
            throw WorldViewException.alreadyJoinedSelection();
        }
        PhotographerCandidate newCandidate = new PhotographerCandidate(userId, contact, description, photo);
        photographerCandidateRepository.save(newCandidate);
        return ResponseBuilder.buildSuccessResponse("报名活动成功", null);
    }

    public ResponseVO getAllCandidates() {
        List<PhotographerCandidateVO> allCandidates = photographerCandidateRepository.findAll().stream().map(photographerCandidate -> {
            User user = userRepository.findById(photographerCandidate.getUserId()).orElseThrow(WorldViewException::userNotFound);
            return new PhotographerCandidateVO(photographerCandidate, user);
        }).collect(Collectors.toList());
        return ResponseBuilder.buildSuccessResponse("获取所选手成功", allCandidates);
    }

    public ResponseVO voteFor(User currentUser, String userVotedEmail) {
        Integer userVotedId = userRepository.findByEmail(userVotedEmail).orElseThrow(WorldViewException::userNotFound).getId();
        Integer userVotingId = currentUser.getId();
        // can not vote for self
        if (userVotedId.equals(userVotingId)) {
            throw WorldViewException.cannotVoteForSelf();
        }
        // update info of candidate
        PhotographerCandidate candidateVoting = photographerCandidateRepository.findByUserId(userVotingId).orElseThrow(WorldViewException::candidateNotFound);
        PhotographerCandidate candidateVoted = photographerCandidateRepository.findByUserId(userVotedId).orElseThrow(WorldViewException::candidateNotFound);
        // check if it has voted for the user
        if (candidateVoting.hasVoted(userVotedId)) {
            throw WorldViewException.alreadyVoted();
        }
        candidateVoted.beVoted();
        candidateVoting.voteFor(userVotedId);
        //save
        photographerCandidateRepository.save(candidateVoted);
        photographerCandidateRepository.save(candidateVoting);
        return ResponseBuilder.buildSuccessResponse("投票成功", null);
    }

    public ResponseVO hasVoted(User currentUser, String userVotedEmail) {
        Integer userVotedId = userRepository.findByEmail(userVotedEmail).orElseThrow(WorldViewException::userNotFound).getId();
        Integer userVotingId = currentUser.getId();
        // check if it has voted
        PhotographerCandidate candidateVoting = photographerCandidateRepository.findByUserId(userVotingId).orElseThrow(WorldViewException::candidateNotFound);
        if (candidateVoting.hasVoted(userVotedId)) {
            return ResponseBuilder.buildSuccessResponse("已投票", null);
        } else {
            return ResponseBuilder.buildSuccessResponse("未投票", null);
        }
    }

    @Override
    public Integer getEventId() {
        return 3;
    }

}
