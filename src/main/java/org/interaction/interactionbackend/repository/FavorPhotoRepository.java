package org.interaction.interactionbackend.repository;

import org.interaction.interactionbackend.po.FavorPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 存储用户点赞过的图片
 */
@Repository
public interface FavorPhotoRepository extends JpaRepository<FavorPhoto, Integer> {

    Optional<FavorPhoto> findByUserIdAndUrl(Integer userId, String url);

    List<FavorPhoto> findAllByUserId(Integer userId);

    int countByUrl(String url);
}
