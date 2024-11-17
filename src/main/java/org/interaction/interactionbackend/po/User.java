package org.interaction.interactionbackend.po;


import lombok.Getter;
import lombok.Setter;
import org.interaction.interactionbackend.enums.Role;
import org.interaction.interactionbackend.vo.UserVO;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "headImg")
    private String headImg;

    @Column(name = "uname")
    private String uname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "sex")
    private String sex;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "qq")
    private String qq;

    @Column(name = "wechat")
    private String wechat;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private Role role;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public boolean resetInfo(UserVO VO) {
        this.headImg = Optional.ofNullable(VO.getHeadImg()).orElse(this.headImg);
        this.uname = Optional.ofNullable(VO.getUname()).orElse(this.uname);
        this.phone = Optional.ofNullable(VO.getPhone()).orElse(this.phone);
        this.email = Optional.ofNullable(VO.getEmail()).orElse(this.email);
        this.phone = Optional.ofNullable(VO.getPhone()).orElse(this.phone);
        this.sex = Optional.ofNullable(VO.getSex()).orElse(this.sex);
        this.birthday = Optional.ofNullable(VO.getBirthday()).orElse(this.birthday);
        this.qq = Optional.ofNullable(VO.getQq()).orElse(this.qq);
        this.wechat = Optional.ofNullable(VO.getWechat()).orElse(this.wechat);
        return true;
    }

    public User() {}
}