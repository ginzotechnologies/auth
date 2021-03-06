package com.spring.auth.scope.infrastructure.repositories.ports;

import com.spring.auth.exceptions.application.NotFoundException;
import com.spring.auth.scope.domain.Scope;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author diegotobalina created on 24/06/2020
 */
public interface FindScopePort {
    Page<Scope> search(Long id, String name, String description, String value, int page, int size);

    List<Scope> findAllByIds(List<Long> ids);

    List<Scope> findAll();

    Scope findById(Long scopeId) throws NotFoundException;

    Scope findByValue(String value) throws NotFoundException;
}
