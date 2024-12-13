package org.interaction.interactionbackend.exception;

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

    public static WorldViewException hasOperated() {
        return new WorldViewException("重复操作");
    }

    public static WorldViewException hasNotOperated() {
        return new WorldViewException("尚未操作");
    }

    public static WorldViewException memberNotFound() {
        return new WorldViewException("成员未找到");
    }

    public static WorldViewException selfOperationNotAllowed() {
        return new WorldViewException("不能对自身进行操作");
    }
    public static WorldViewException photoExist() {
        return new WorldViewException("图片已存在!");
    }
    public static WorldViewException photoNotFound() {
        return new WorldViewException("图片不存在!");
    }
    public static WorldViewException permissionDeny() {
        return new WorldViewException("权限不足!");
    }

    public static WorldViewException invalidKey() {
        return new WorldViewException("无效的key值");
    }
}

