package com.spring.auth.role.application;

import com.spring.auth.anotations.components.UseCase;
import com.spring.auth.exceptions.application.DuplicatedKeyException;
import com.spring.auth.exceptions.application.NotFoundException;
import com.spring.auth.role.application.ports.UpdateScopesFromRolePort;
import com.spring.auth.role.domain.Role;
import com.spring.auth.role.infrastructure.repositories.ports.FindRolePort;
import com.spring.auth.role.infrastructure.repositories.ports.UpdateRolePort;
import com.spring.auth.scope.domain.Scope;
import com.spring.auth.scope.infrastructure.repositories.ports.FindScopePort;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @author diegotobalina created on 24/06/2020
 */
@UseCase
@AllArgsConstructor
public class UpdateScopesFromRoleUseCase implements UpdateScopesFromRolePort {

    private final FindScopePort findScopePort;
    private final FindRolePort findRolePort;
    private final UpdateRolePort updateRolePort;

    @Override
    public Role update(Role role, List<Long> scopeIds) throws DuplicatedKeyException {
        List<Scope> scopes = findScopePort.findAllByIds(scopeIds);
        role.updateScopes(scopes);
        return updateRolePort.update(role);
    }

    @Override
    public Role update(Long roleId, List<Long> scopeIds) throws DuplicatedKeyException, NotFoundException {
        Role role = findRolePort.findById(roleId);
        return update(role, scopeIds);
    }
}
