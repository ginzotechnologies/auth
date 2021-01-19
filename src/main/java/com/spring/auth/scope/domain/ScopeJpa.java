package com.spring.auth.scope.domain;

import com.spring.auth.shared.domain.Auditable;
import lombok.*;

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
@NoArgsConstructor
@Table(name = "spring_scope")
public class ScopeJpa extends Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    private String name;

    private String description;

    private String value;

    public ScopeJpa(
            Date createdAt,
            Date lastModified,
            String createdBy,
            String lastModifiedBy,
            Long id,
            String name,
            String description,
            String value) {
        super(createdAt, lastModified, createdBy, lastModifiedBy);
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
    }
}
