package com.spring.auth.user.infrastructure.dto.output;

import com.spring.auth.user.domain.User;
import lombok.Getter;
import lombok.ToString;

/**
 * @author diegotobalina created on 24/06/2020
 */
@Getter
@ToString
public class RegisterOutputDto {

    private final Long userId; // user id of the new user
    private final String username; // username of the new user
    private final String email; // email of the new user

    public RegisterOutputDto(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
