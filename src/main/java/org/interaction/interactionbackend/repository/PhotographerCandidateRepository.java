package org.interaction.interactionbackend.repository;

import org.interaction.interactionbackend.po.PhotographerCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographerCandidateRepository extends JpaRepository<PhotographerCandidate, Integer> {
    PhotographerCandidate findByUserId(Integer userId);
}
