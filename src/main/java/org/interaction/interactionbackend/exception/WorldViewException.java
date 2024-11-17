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

    public static WorldViewException phoneExist() {
        return new WorldViewException("手机号已存在!");
    }

    public static WorldViewException pwdWrong() {
        return new WorldViewException("密码错误!");
    }

    public static WorldViewException pwdNotSame() {
        return new WorldViewException("两次密码不一致!");
    }

}

