package org.interaction.interactionbackend.repository;

import org.interaction.interactionbackend.po.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    Optional<Participant> findByUserIdAndEventId(Integer userId, Integer eventId);
}
