package com.spring.auth.client.infrastructure.repositories.jpa;

import com.spring.auth.client.domain.ClientJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepositoryJpa extends JpaRepository<ClientJpa, Long> {
    boolean existsByClientIdInAndIdNotIn(List<Long> clientIds, List<Long> ids);

    Optional<ClientJpa> findByClientId(Long clientId);

    Optional<ClientJpa> findById(Long clientId);
}
