package org.interaction.interactionbackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.interaction.interactionbackend.po.Member;
import org.interaction.interactionbackend.po.User;

@Getter
@Setter
@NoArgsConstructor
public class MemberVO {

    private String email;
    private String headImg;
    private String uname;
    private String contact;
    private String description;
    private String photo;

    public MemberVO(Member member, User user) {
        this.email = user.getEmail();
        this.headImg = user.getHeadImg();
        this.uname = user.getUname();
        if (member == null) {
            this.contact = null;
            this.description = null;
            this.photo = null;
        }
        else {
            this.contact = member.getContact();
            this.description = member.getDescription();
            this.photo = member.getPhoto();
        }
    }
}
