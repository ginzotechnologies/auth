package com.spring.auth.user.infrastructure.dto.output;

import com.spring.auth.role.domain.Role;
import com.spring.auth.scope.domain.Scope;
import com.spring.auth.user.domain.User;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author diegotobalina created on 24/06/2020
 */
@Getter
@ToString
public class SearchUsersOutputDto {

    private final Long id; // id of the user
    private final String username; // username of the user
    private final String email; // email of the user
    private final List<Long> roles; // role ids of the user
    private final List<Long> scopes; // scope ids of the user
    private final int max_sessions; // max sessions of the user

    public SearchUsersOutputDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles().stream().map(Role::getId).collect(Collectors.toList());
        this.scopes = user.getScopes().stream().map(Scope::getId).collect(Collectors.toList());
        this.max_sessions = user.getMaxSessions();
    }
}
