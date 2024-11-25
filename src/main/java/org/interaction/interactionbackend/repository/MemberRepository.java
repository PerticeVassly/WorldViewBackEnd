package org.interaction.interactionbackend.repository;

import org.interaction.interactionbackend.po.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByUserId(Integer userId);
}
