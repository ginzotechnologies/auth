package com.spring.auth.authorization.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author diegotobalina created on 24/06/2020
 */
@Getter
@ToString
public class TokenInfo {

    private final String token;
    private final Date issuedAt;
    private final Date expiration;
    private final Long userId;
    private List<String> roles;
    private List<String> scopes;

    public TokenInfo(String token, Date issuedAt, Date expiration, Long userId) {
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiration = expiration;
        this.userId = userId;
    }

    public TokenInfo(
            String token,
            Date issuedAt,
            Date expiration,
            Long userId,
            List<String> roles,
            List<String> scopes) {
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiration = expiration;
        this.userId = userId;
        this.roles = roles;
        this.scopes = scopes;
    }
}
