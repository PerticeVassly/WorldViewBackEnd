package org.interaction.interactionbackend.repository;

import org.interaction.interactionbackend.po.Collection;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer> {

    Optional<Collection> findByCollectingIdAndCollectedId(Integer collectingId, Integer collectedId);

    List<Collection> findAllByCollectingId(Integer collectingId);

    List<Collection> findAllByCollectedId(Integer collectedId);
}
