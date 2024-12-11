package org.interaction.interactionbackend.repository;

import org.interaction.interactionbackend.enums.PhotoTheme;
import org.interaction.interactionbackend.po.Photo;
import org.interaction.interactionbackend.vo.PhotoVO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    List<Photo> findByTheme(PhotoTheme theme, PageRequest pageRequest);

    List<Photo> findByUserId(Integer userId, PageRequest pageRequest);

    Optional<Photo> findByUrl(String url);

    List<Photo> findAllByUserId(Integer userId);
}
