package com.spring.auth.user.domain;

import com.spring.auth.role.domain.RoleJpa;
import com.spring.auth.scope.domain.ScopeJpa;
import com.spring.auth.shared.domain.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author diegotobalina created on 24/06/2020
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "spring_user")
public class UserJpa extends Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    private String username;

    private String email;

    private String password;

    @OneToMany(mappedBy = "")
    private List<RoleJpa> roles;

    @OneToMany(mappedBy = "")
    private List<ScopeJpa> scopes;

    private int maxSessions = 10;
    private boolean locked = false;
    private boolean loggedWithGoogle = false;
    private boolean emailVerified = false;

    public UserJpa(
            Date createdAt,
            Date lastModified,
            String createdBy,
            String lastModifiedBy,
            Long id,
            String username,
            String email,
            String password,
            List<RoleJpa> roles,
            List<ScopeJpa> scopes,
            int maxSessions,
            boolean locked,
            boolean loggedWithGoogle,
            boolean emailVerified) {
        super(createdAt, lastModified, createdBy, lastModifiedBy);
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.scopes = scopes;
        this.maxSessions = maxSessions;
        this.locked = locked;
        this.loggedWithGoogle = loggedWithGoogle;
        this.emailVerified = emailVerified;
    }
}
