package com.spring.auth.session.domain;

import com.spring.auth.shared.domain.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author diegotobalina created on 24/06/2020
 */

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@Table(name = "spring_session")
public class SessionJpa extends Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    private String token;

    private Date issuedAt;

    private Date expiration;

    private Long userId;

    public SessionJpa(
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
}
