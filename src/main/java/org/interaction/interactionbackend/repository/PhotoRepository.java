package org.interaction.interactionbackend.repository;

import org.interaction.interactionbackend.enums.PhotoTheme;
import org.interaction.interactionbackend.po.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    Optional<Photo> findByUrl(String url);

    List<Photo> findByUserId(Integer userId);

    List<Photo> findByTheme(PhotoTheme theme);

}
