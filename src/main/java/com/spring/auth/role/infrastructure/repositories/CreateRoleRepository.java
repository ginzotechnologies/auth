package com.spring.auth.role.infrastructure.repositories;

import com.spring.auth.exceptions.application.DuplicatedKeyException;
import com.spring.auth.role.domain.Role;
import com.spring.auth.role.domain.RoleJpa;
import com.spring.auth.role.domain.RoleMapper;
import com.spring.auth.role.infrastructure.repositories.jpa.RoleRepositoryJpa;
import com.spring.auth.role.infrastructure.repositories.ports.CheckRolesConstraintsPort;
import com.spring.auth.role.infrastructure.repositories.ports.CreateRolePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author diegotobalina created on 24/06/2020
 */
@Repository
@AllArgsConstructor
public class CreateRoleRepository implements CreateRolePort {

    private final RoleRepositoryJpa roleRepositoryJpa;
    private final CheckRolesConstraintsPort checkRolesConstraintsPort;

    @Override
    public Role create(Role role) throws DuplicatedKeyException {
        checkRolesConstraintsPort.check(List.of(role));
        RoleJpa roleJpa = RoleMapper.parse(role);
        RoleJpa savedRoleJpa = roleRepositoryJpa.save(roleJpa);
        return RoleMapper.parse(savedRoleJpa);
    }
}
