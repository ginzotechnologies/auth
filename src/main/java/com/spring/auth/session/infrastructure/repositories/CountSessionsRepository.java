package com.spring.auth.session.infrastructure.repositories;

import com.spring.auth.session.infrastructure.repositories.jpa.SessionRepositoryJpa;
import com.spring.auth.session.infrastructure.repositories.ports.CountSessionPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author diegotobalina created on 24/06/2020
 */
@Repository
@AllArgsConstructor
public class CountSessionsRepository implements CountSessionPort {

    private final SessionRepositoryJpa sessionRepositoryJpa;

    @Override
    public int countAllByUserId(Long userId) {
        return sessionRepositoryJpa.countAllByUserId(userId);
    }
}
