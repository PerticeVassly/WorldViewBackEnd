package org.interaction.interactionbackend.repository;

import org.interaction.interactionbackend.po.FavorPhotographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 存储用户点赞的摄影师
 */
@Repository
public interface FavorPhotographerRepository extends JpaRepository<FavorPhotographer, Integer> {
    Optional<FavorPhotographer> findByFavoringIdAndFavoredId(Integer favoringId, Integer favoredId);
}
