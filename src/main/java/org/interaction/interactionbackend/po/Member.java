package org.interaction.interactionbackend.po;

import lombok.Getter;
import lombok.Setter;
import org.interaction.interactionbackend.vo.MemberVO;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

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

    public Member(Integer userId, String contact, String description, String photo) {
        this.userId = userId;
        this.contact = contact;
        this.description = description;
        this.photo = photo;
    }

    public Member() {}
}
