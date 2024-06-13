package com.project.pc.repository;

import com.project.pc.domain.Member;
import com.project.pc.repository.search.MemberSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String>, MemberSearch {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.id = :id and m.social = false")
    Optional<Member> getWithRoles(String id);

    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> findByEmail(String email);

    @Query("select m.email from Member m where m.email = :email")
    String emailCheck(String email);
}
