package org.interaction.interactionbackend.po;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Getter
@Setter
public class PhotographerCandidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "userId", nullable = false, unique = true)
    private Integer userId;

    @Column(name = "contact",nullable = false)
    private String contact;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "photo",nullable = false)
    private String photo;

    @Column(name = "votes",nullable = false)
    private Integer votes = 0; // votes got by the candidate

    @Column(name = "votingFor")
    private Integer votingfor; // the candidate who is voted By this candidate

    public PhotographerCandidate(int userId, String contact, String description, String photo) {
        this.userId = userId;
        this.contact = contact;
        this.description = description;
        this.photo = photo;
        this.votes = 0;
    }

    public PhotographerCandidate() {}

    public void beVoted() {
        this.votes++;
    }

    public void voteFor(int userId) {
        this.votingfor = userId;
    }

    public boolean hasVoted(int userId) {
        if (this.votingfor == null) {
            return false;
        }
        return this.votingfor == userId;
    }
}
