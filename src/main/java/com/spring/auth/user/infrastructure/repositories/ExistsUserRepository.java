package com.spring.auth.user.infrastructure.repositories;

import com.spring.auth.user.infrastructure.repositories.jpa.UserRepositoryJpa;
import com.spring.auth.user.infrastructure.repositories.ports.ExistsUserPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author diegotobalina created on 24/06/2020
 */
@Repository
@AllArgsConstructor
public class ExistsUserRepository implements ExistsUserPort {

    private final UserRepositoryJpa userRepositoryJpa;

    @Override
    public boolean existsByUsername(String username) {
        return userRepositoryJpa.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepositoryJpa.existsByEmail(email);
    }
}
