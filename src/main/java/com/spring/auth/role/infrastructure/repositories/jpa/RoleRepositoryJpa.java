package com.spring.auth.role.infrastructure.repositories.jpa;

import com.spring.auth.role.domain.RoleJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author diegotobalina created on 24/06/2020
 */
@Repository
public interface RoleRepositoryJpa extends JpaRepository<RoleJpa, Long> {
    Optional<RoleJpa> findByValue(String value);

    List<RoleJpa> findAllByScopesId(Long scopeId);

    List<RoleJpa> findAllByIdIn(List<Long> roleIds);

    List<RoleJpa> findAllByValueIn(List<String> value);

    boolean existsByValue(String value);

    boolean existsByValueAndIdNot(String value, Long id);

    boolean existsByValueInAndIdNotIn(List<String> values, List<Long> ids);
}
