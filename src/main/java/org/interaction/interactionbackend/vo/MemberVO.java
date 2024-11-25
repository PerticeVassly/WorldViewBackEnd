package org.interaction.interactionbackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.interaction.interactionbackend.po.Member;

@Getter
@Setter
@NoArgsConstructor
public class MemberVO {

    private String contact;
    private String description;
    private String photo;

    public MemberVO(Member member) {
        this.contact = member.getContact();
        this.description = member.getDescription();
        this.photo = member.getPhoto();
    }
}
