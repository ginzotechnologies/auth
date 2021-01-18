package com.spring.auth.authorization.infrastructure.dto.output;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author diegotobalina created on 31/07/2020
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuthorizeOutputDto {
    private String id_token;

    public AuthorizeOutputDto(String token) {
        this.id_token = token;
    }
}

