package com.spring.auth.role.infrastructure.repositories;

import com.spring.auth.exceptions.application.DuplicatedKeyException;
import com.spring.auth.role.domain.Role;
import com.spring.auth.role.infrastructure.repositories.jpa.RoleRepositoryJpa;
import com.spring.auth.role.infrastructure.repositories.ports.CheckRolesConstraintsPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author diegotobalina created on 24/06/2020
 */
@Repository
@AllArgsConstructor
public class CheckRolesConstraintsRepository implements CheckRolesConstraintsPort {

    private final RoleRepositoryJpa roleRepositoryJpa;

    public void check(List<Role> roles) throws DuplicatedKeyException {
        List<Long> roleIds = getRoleIds(roles);
        List<String> roleValues = getRoleValues(roles);
        if (isValueDuplicated(roleIds, roleValues)) {
            throw new DuplicatedKeyException("duplicated value in: " + roleValues);
        }
    }

    private List<String> getRoleValues(List<Role> roles) {
        return roles.stream().map(Role::getValue).collect(Collectors.toList());
    }

    private List<Long> getRoleIds(List<Role> roles) {
        return roles.stream().map(Role::getId).collect(Collectors.toList());
    }

    private boolean isValueDuplicated(List<Long> roleIds, List<String> roleValues) {
        return roleRepositoryJpa.existsByValueInAndIdNotIn(roleValues, roleIds);
    }
}
