package org.interaction.interactionbackend.serviceimpl;

import org.interaction.interactionbackend.exception.WorldViewException;
import org.interaction.interactionbackend.po.Collection;
import org.interaction.interactionbackend.po.Member;
import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.repository.CollectionRepository;
import org.interaction.interactionbackend.repository.MemberRepository;
import org.interaction.interactionbackend.repository.UserRepository;
import org.interaction.interactionbackend.util.ResponseBuilder;
import org.interaction.interactionbackend.vo.CollectionVO;
import org.interaction.interactionbackend.vo.MemberVO;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CommunityServiceImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CollectionRepository collectionRepository;

    public ResponseVO register(User currentUser, String contact, String description, String photo) {
        if (memberRepository.findByUserId(currentUser.getId()).isPresent()) {
            throw WorldViewException.alreadyJoinedService();
        }
        Member newMember = new Member(currentUser.getId(), contact, description, photo);
        memberRepository.save(newMember);
        return ResponseBuilder.buildSuccessResponse("成功注册摄影师", null);
    }

    public ResponseVO getAll() {
        return ResponseBuilder.buildSuccessResponse("成功获取所有摄影师信息", memberRepository.findAll().stream().map(member -> {
            User user = userRepository.findById(member.getUserId()).orElseThrow(WorldViewException::userNotFound);
            return new MemberVO(member, user);
        }).toArray());
    }

    public ResponseVO getCollection(User currentUser) {
        return ResponseBuilder.buildSuccessResponse("成功获取所有收藏信息", collectionRepository.findAllByCollectingId(currentUser.getId()).stream().map(collect -> {
            Integer collectedId = collect.getCollectedId();
            User user = userRepository.findById(collectedId).orElseThrow(WorldViewException::userNotFound);
            Member member = memberRepository.findByUserId(collectedId).orElseThrow(WorldViewException::memberNotFound);
            return new CollectionVO(user, member);
        }));
    }

    public ResponseVO collect(User currentUser, String email) {
        Integer collectedId = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound).getId();
        Integer collectingId = currentUser.getId();
        if (collectingId.equals(collectedId)) {
            throw WorldViewException.cannotCollectOrCancelCollectSelf();
        }
        if (collectionRepository.findByCollectingIdAndCollectedId(collectingId, collectedId).isPresent()) {
            throw WorldViewException.hasCollected();
        }
        Collection newCollection = new Collection(collectingId, collectedId);
        collectionRepository.save(newCollection);
        return ResponseBuilder.buildSuccessResponse("收藏成功", null);
    }

    public ResponseVO cancelCollect(User currentUser, String email) {
        Integer collectedId = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound).getId();
        Integer collectingId = currentUser.getId();
        if (collectedId.equals(collectingId)) {
            throw WorldViewException.cannotCollectOrCancelCollectSelf();
        }
        Collection item = collectionRepository.findByCollectingIdAndCollectedId(collectingId, collectedId).orElseThrow(WorldViewException::hasNotCollected);
        collectionRepository.delete(item);
        return ResponseBuilder.buildSuccessResponse("取消收藏成功", null);
    }

    public ResponseVO hasCollected(User currentUser, String email) {
        Integer collectedId = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound).getId();
        Integer collectingId = currentUser.getId();
        if (collectedId.equals(collectingId)) {
            throw WorldViewException.cannotCollectOrCancelCollectSelf();
        }
        if (collectionRepository.findByCollectingIdAndCollectedId(collectingId, collectedId).isPresent()) {
            return ResponseBuilder.buildSuccessResponse("已收藏", null);
        } else {
            return ResponseBuilder.buildSuccessResponse("未收藏", null);
        }
    }
}
