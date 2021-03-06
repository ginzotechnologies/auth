package com.spring.auth.role.application.ports;

import com.spring.auth.exceptions.application.DuplicatedKeyException;
import com.spring.auth.exceptions.application.NotFoundException;
import com.spring.auth.role.domain.Role;

import java.util.List;

/**
 * @author diegotobalina created on 24/06/2020
 */
public interface AddScopesToRolePort {
    Role add(Role role, List<Long> scopeIds) throws DuplicatedKeyException;

    Role add(Long roleId, List<Long> scopeIds) throws NotFoundException, DuplicatedKeyException;
}
