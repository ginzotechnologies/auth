package com.spring.auth.role.infrastructure.repositories.ports;

import com.spring.auth.exceptions.application.NotFoundException;
import com.spring.auth.role.domain.Role;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author diegotobalina created on 24/06/2020
 */
public interface FindRolePort {

    Page<Role> search(Long id, String name, String description, String value, int page, int size);

    List<Role> findAll();

    List<Role> findAllByIds(List<Long> roleIds);

    List<Role> findAllByScopeId(Long scopeId);

    Role findById(Long roleId) throws NotFoundException;

    Role findByValue(String value) throws NotFoundException;
}
