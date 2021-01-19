package com.spring.auth.session.infrastructure.repositories.jpa;

import com.spring.auth.session.domain.SessionJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author diegotobalina created on 24/06/2020
 */
@Repository
public interface SessionRepositoryJpa extends JpaRepository<SessionJpa, String> {
    Optional<SessionJpa> findByToken(String value);

    List<SessionJpa> findAllByUserId(Long userId);

    int countAllByUserId(Long userId);

    Optional<SessionJpa> findFirstByUserIdOrderByExpiration(Long userId);

    List<SessionJpa> findAllByExpirationBefore(Date expirationDate);

    boolean existsByToken(String token);
}
