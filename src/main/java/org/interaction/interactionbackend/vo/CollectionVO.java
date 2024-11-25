package org.interaction.interactionbackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.interaction.interactionbackend.po.Member;
import org.interaction.interactionbackend.po.User;

@Getter
@Setter
@NoArgsConstructor
public class CollectionVO {
    private String email;
    private String uname;
    private String contact;
    private String description;
    private String photo;

    public CollectionVO(User user, Member member) {
        this.email = user.getEmail();
        this.uname = user.getUname();
        this.contact = member.getContact();
        this.description = member.getDescription();
        this.photo = member.getPhoto();
    }

}
