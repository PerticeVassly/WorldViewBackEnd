package org.interaction.interactionbackend.repository;

import org.interaction.interactionbackend.po.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    Participant findByUserIdAndAndEventId(Integer userId, Integer eventId);
}
