package com.spring.auth.authorization.infrastructure.dto.output;

import com.spring.auth.session.domain.Session;
import com.spring.auth.util.TokenUtil;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

/**
 * @author diegotobalina created on 24/06/2020
 */
@Getter
@ToString
public class LoginOutputDto {

    private final String session_token;
    private final Date session_issued_at;
    private final Date session_expiration;
    private final Long session_user_id;
    private final String access_id_token;
    private final Date access_issued_at;
    private final Date access_expiration;
    private final Long access_user_id;

    public LoginOutputDto(Session session, TokenUtil.JwtWrapper access) {
        this.session_token = TokenUtil.addBearerPrefix(session.getToken());
        this.session_issued_at = session.getIssuedAt();
        this.session_expiration = session.getExpiration();
        this.session_user_id = session.getUserId();
        this.access_id_token = TokenUtil.addBearerPrefix(access.getToken());
        this.access_issued_at = access.getIssuedAt();
        this.access_expiration = access.getExpiration();
        this.access_user_id = access.getUserId();
    }
}
