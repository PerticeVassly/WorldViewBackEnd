package org.interaction.interactionbackend.serviceimpl;

import org.interaction.interactionbackend.enums.PhotoTheme;
import org.interaction.interactionbackend.exception.WorldViewException;
import org.interaction.interactionbackend.po.*;
import org.interaction.interactionbackend.repository.*;
import org.interaction.interactionbackend.util.ResponseBuilder;
import org.interaction.interactionbackend.vo.MemberVO;
import org.interaction.interactionbackend.vo.PhotoVO;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityServiceImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CollectionRepository collectionRepository;

    @Autowired
    FavorPhotographerRepository favorPhotographerRepository;

    @Autowired
    PhotoRepository photoRepository;


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

    public ResponseVO getCollection(String email) {
        Integer collectingId = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound).getId();
        return ResponseBuilder.buildSuccessResponse("成功获取所有收藏信息", collectionRepository.findAllByCollectingId(collectingId).stream().map(collect -> {
            Integer collectedId = collect.getCollectedId();
            User user = userRepository.findById(collectedId).orElseThrow(WorldViewException::userNotFound);
            Member member = memberRepository.findByUserId(collectedId).orElseThrow(WorldViewException::memberNotFound);
            return new MemberVO(member, user);
        }));
    }

    public ResponseVO collect(User currentUser, String email) {
        Integer collectedId = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound).getId();
        Integer collectingId = currentUser.getId();
        if (collectingId.equals(collectedId)) {
            throw WorldViewException.selfOperationNotAllowed();
        }
        if (collectionRepository.findByCollectingIdAndCollectedId(collectingId, collectedId).isPresent()) {
            throw WorldViewException.hasOperated();
        }
        Collection newCollection = new Collection(collectingId, collectedId);
        collectionRepository.save(newCollection);
        return ResponseBuilder.buildSuccessResponse("收藏成功", null);
    }

    public ResponseVO cancelCollect(User currentUser, String email) {
        Integer collectedId = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound).getId();
        Integer collectingId = currentUser.getId();
        if (collectedId.equals(collectingId)) {
            throw WorldViewException.selfOperationNotAllowed();
        }
        Collection item = collectionRepository.findByCollectingIdAndCollectedId(collectingId, collectedId).orElseThrow(WorldViewException::hasNotOperated);
        collectionRepository.delete(item);
        return ResponseBuilder.buildSuccessResponse("取消收藏成功", null);
    }

    public ResponseVO hasCollected(User currentUser, String email) {
        Integer collectedId = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound).getId();
        Integer collectingId = currentUser.getId();
        if (collectedId.equals(collectingId)) {
            throw WorldViewException.selfOperationNotAllowed();
        }
        if (collectionRepository.findByCollectingIdAndCollectedId(collectingId, collectedId).isPresent()) {
            return ResponseBuilder.buildSuccessResponse("已收藏", null);
        } else {
            return ResponseBuilder.buildSuccessResponse("未收藏", null);
        }
    }

    public ResponseVO favorite(User currentUser, String email) {
        Integer favoredId = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound).getId();
        Integer favoringId = currentUser.getId();
        if (favoringId.equals(favoredId)) {
            throw WorldViewException.selfOperationNotAllowed();
        }
        if (favorPhotographerRepository.findByFavoringIdAndFavoredId(favoringId, favoredId).isPresent()) {
            throw WorldViewException.hasOperated();
        }
        favorPhotographerRepository.save(new FavorPhotographer(favoringId, favoredId));
        return ResponseBuilder.buildSuccessResponse("点赞成功", null);

    }

    public ResponseVO cancelFavorite(User currentUser, String email) {
        Integer favoredId = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound).getId();
        Integer favoringId = currentUser.getId();
        if (favoringId.equals(favoredId)) {
            throw WorldViewException.selfOperationNotAllowed();
        }
        FavorPhotographer item = favorPhotographerRepository.findByFavoringIdAndFavoredId(favoringId, favoredId).orElseThrow(WorldViewException::hasNotOperated);
        favorPhotographerRepository.delete(item);
        return ResponseBuilder.buildSuccessResponse("取消点赞成功", null);
    }

    public ResponseVO hasFavorited(User currentUser, String email) {
        Integer favoredId = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound).getId();
        Integer favoringId = currentUser.getId();
        if (favoringId.equals(favoredId)) {
            throw WorldViewException.selfOperationNotAllowed();
        }
        if (favorPhotographerRepository.findByFavoringIdAndFavoredId(favoringId, favoredId).isPresent()) {
            return ResponseBuilder.buildSuccessResponse("已点赞", null);
        } else {
            return ResponseBuilder.buildSuccessResponse("未点赞", null);
        }
    }

    public ResponseVO getFans(String email) {
        // 所有collection中收藏了email的用户
        Integer collectedId = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound).getId();
        return ResponseBuilder.buildSuccessResponse("成功获取所有粉丝", collectionRepository.findAllByCollectedId(collectedId).stream().map(collection -> {
            User user = userRepository.findById(collection.getCollectingId()).orElseThrow(WorldViewException::userNotFound);
            return new MemberVO(memberRepository.findByUserId(collection.getCollectedId()).orElseThrow(WorldViewException::memberNotFound), user);
        }).toArray());
    }

    public ResponseVO upload(User currentUser, List<HashMap<String, String>> info) {
        Member member = memberRepository.findByUserId(currentUser.getId()).orElseThrow(WorldViewException::memberNotFound);
        photoRepository.saveAll(info.stream().map(
                item -> new Photo(currentUser.getId(), item.get("url"), item.get("title"), item.get("description"), PhotoTheme.valueOf(item.get("theme")))).collect(Collectors.toList()));
        return ResponseBuilder.buildSuccessResponse("上传成功", null);
    }

    public ResponseVO getAllPhotos(String email) {
        Integer userId = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound).getId();
        List<String> photos = photoRepository.findAllByUserId(userId).stream().map(Photo::getUrl).collect(Collectors.toList());
        return ResponseBuilder.buildSuccessResponse("成功获取所有照片", photos);
    }

    public ResponseVO hasJoined(User currentUser) {
        if (memberRepository.findByUserId(currentUser.getId()).isPresent()) {
            return ResponseBuilder.buildSuccessResponse("已加入", null);
        } else {
            return ResponseBuilder.buildSuccessResponse("未加入", null);
        }
    }

    /* 获取被点赞数 */
    public ResponseVO getFavoredNum(String email) {
        Integer favoredId = userRepository.findByEmail(email).orElseThrow(WorldViewException::userNotFound).getId();
        HashMap<String, Integer> res = new HashMap<>();
        res.put("number", favorPhotographerRepository.findAllByFavoredId(favoredId).size());
        return ResponseBuilder.buildSuccessResponse("成功获取被点赞数", res);
    }
}
