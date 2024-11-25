package org.interaction.interactionbackend.po;

import lombok.Getter;
import lombok.Setter;
import org.interaction.interactionbackend.vo.MemberVO;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    @Column(name = "contact")
    private String contact;

    @Column(name = "description")
    private String description;

    @Column(name = "photo")
    private String photo;

    public Member(User user, MemberVO VO) {
        this.userId = user.getId();
        this.contact = VO.getContact();
        this.description = VO.getDescription();
        this.photo = VO.getPhoto();
    }

    public Member() {}
}
