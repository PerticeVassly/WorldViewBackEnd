package org.interaction.interactionbackend.repository;

import org.interaction.interactionbackend.po.PhotographerCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotographerCandidateRepository extends JpaRepository<PhotographerCandidate, Integer> {
    Optional<PhotographerCandidate> findByUserId(Integer userId);
}
