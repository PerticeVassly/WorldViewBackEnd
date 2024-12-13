package org.interaction.interactionbackend.repository;

import org.interaction.interactionbackend.enums.PhotoTheme;
import org.interaction.interactionbackend.po.Photo;
import org.interaction.interactionbackend.vo.PhotoVO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ls.LSInput;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    Optional<Photo> findByUrl(String url);

    List<Photo> findAllByUserId(Integer userId);

    List<Photo> findAllByUserId(Integer userId , Pageable pageable);

    List<Photo> findAllByRankingTag(Boolean rankingTag, Pageable pageable);

    List<Photo> findAllByNewTag(Boolean newTag, Pageable pageable);

    List<Photo> findAllByRecommendTag(Boolean recommendTag, Pageable pageable);
}
