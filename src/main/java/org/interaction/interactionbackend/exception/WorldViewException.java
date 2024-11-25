package org.interaction.interactionbackend.exception;

import org.aspectj.weaver.World;

import java.io.IOException;

public class WorldViewException extends RuntimeException {

    public WorldViewException(String message) {
            super(message);
    }

    // the format of email is wrong
    public static WorldViewException emailFormatWrong() {
        return new WorldViewException("邮箱格式错误!");
    }

    // no login
    public static WorldViewException notLogin() {
        return new WorldViewException("未登录!");
    }

    public static WorldViewException emailExist() {
        return new WorldViewException("邮箱已存在!");
    }

    public static WorldViewException pwdWrong() {
        return new WorldViewException("密码错误!");
    }

    public static WorldViewException pwdNotSame() {
        return new WorldViewException("两次密码不一致!");
    }

    public static WorldViewException alreadyRegisteredEvent() {
        return new WorldViewException("已经报名过该活动!");
    }

    public static WorldViewException alreadyJoinedSelection() {
        return new WorldViewException("已经参加此评选!");
    }

    public static WorldViewException userNotFound() {
        return new WorldViewException("用户不存在!");
    }

    public static WorldViewException alreadyVoted() {
        return new WorldViewException("已经投过票了!");
    }

    public static WorldViewException cannotVoteForSelf() {
        return new WorldViewException("不能为自己投票!");
    }

    public static WorldViewException candidateNotFound() {
        return new WorldViewException("选手不存在!");
    }

    public static WorldViewException alreadyJoinedService() {
        return new WorldViewException("已经加入服务");
    }

    public static WorldViewException hasCollected() {
        return new WorldViewException("重复收藏");
    }

    public static WorldViewException hasNotCollected() {
        return new WorldViewException("还未收藏");
    }

    public static WorldViewException memberNotFound() {
        return new WorldViewException("成员未找到");
    }

    public static WorldViewException cannotCollectOrCancelCollectSelf() {
        return new WorldViewException("不能收藏或者取消收藏自己");
    }
}

