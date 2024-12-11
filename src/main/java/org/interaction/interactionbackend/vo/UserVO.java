package org.interaction.interactionbackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.interaction.interactionbackend.enums.Role;
import org.interaction.interactionbackend.po.User;

@Getter
@Setter
@NoArgsConstructor
public class UserVO {
    private String headImg;
    private String uname;
    private String email;
    private String phone;
    private String sex;
    private String birthday;
    private String qq;
    private String wechat;
    private Role role;

    public UserVO(User user) {
        this.headImg = user.getHeadImg();
        this.uname = user.getUname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.sex = user.getSex();
        this.birthday = user.getBirthday();
        this.qq = user.getQq();
        this.wechat = user.getWechat();
        this.role = user.getRole();
    }

}
