package com.spring.auth.session.domain;

import com.spring.auth.shared.domain.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

/**
 * @author diegotobalina created on 24/06/2020
 */
@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
public class Session extends Auditable implements Comparable<Session> {

    private Long id;
    private String token;
    private Date issuedAt;
    private Date expiration;
    private Long userId;

    public Session(
            Date createdAt,
            Date lastModified,
            String createdBy,
            String lastModifiedBy,
            Long id,
            String token,
            Date issuedAt,
            Date expiration,
            Long userId) {
        super(createdAt, lastModified, createdBy, lastModifiedBy);
        this.id = id;
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiration = expiration;
        this.userId = userId;
    }

    public Session(final Long userId) {
        this.token = generateToken();
        this.issuedAt = new Date();
        this.expiration = generateExpiration();
        this.userId = userId;
    }

    public Boolean isValid() {
        return this.getExpiration().getTime() >= System.currentTimeMillis();
    }

    public void refresh() {
        this.expiration = this.generateExpiration();
    }

    private String generateToken() {
        return System.currentTimeMillis() + "-" + UUID.randomUUID().toString();
    }

    private Date generateExpiration() {
        return new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000));
    }

    @Override
    public int compareTo(final Session o) {
        return getExpiration().compareTo(o.getExpiration());
    }
}
