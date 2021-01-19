package com.spring.auth.role.domain;

import com.spring.auth.scope.domain.ScopeJpa;
import com.spring.auth.shared.domain.Auditable;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author diegotobalina created on 24/06/2020
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spring_role")
public class RoleJpa extends Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    private String name;

    private String description;

    private String value;

    @OneToMany(mappedBy = "")
    private List<ScopeJpa> scopes;

    public RoleJpa(
            Date createdAt,
            Date lastModified,
            String createdBy,
            String lastModifiedBy,
            Long id,
            String name,
            String description,
            String value,
            List<ScopeJpa> scopes) {
        super(createdAt, lastModified, createdBy, lastModifiedBy);
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
        this.scopes = scopes;
    }
}
