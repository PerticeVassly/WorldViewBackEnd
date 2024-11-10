package org.interaction.interactionbackend.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private Integer eventId;

    public Participant(int userId, String contact, Integer eventId) {
        this.userId = userId;
        this.contact = contact;
        this.eventId = eventId;
    }

    public Participant() {}
}
