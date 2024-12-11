package org.interaction.interactionbackend.repository;

import org.interaction.interactionbackend.po.FavorPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 存储用户点赞过的图片
 */
@Repository
public interface FavorPhotoRepository extends JpaRepository<FavorPhoto, Integer> {

    Optional<FavorPhoto> findByFavoringIdAndFavoredId(Integer favoringId, Integer favoredId);

    List<FavorPhoto> findAllByFavoringId(Integer favoringId);
}
