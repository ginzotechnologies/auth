package com.spring.auth.scope.infrastructure.repositories.jpa;

import com.spring.auth.scope.domain.ScopeJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScopeRepositoryJpa extends JpaRepository<ScopeJpa, String> {
    Optional<ScopeJpa> findByValue(String value);

    List<ScopeJpa> findAllByIdIn(List<Long> ids);

    boolean existsByValue(String value);
}
