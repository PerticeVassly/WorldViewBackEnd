package org.interaction.interactionbackend.po;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/**
 * the information of participant of a event
 */
@Entity
@Getter
@Setter
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    @Column(name = "contact",nullable = false)
    private String contact;

    @Column(name = "eventId",nullable = false)
    private Integer eventId;

    public Participant(int userId, String contact, Integer eventId) {
        this.userId = userId;
        this.contact = contact;
        this.eventId = eventId;
    }

    public Participant() {}
}
