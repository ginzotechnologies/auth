package com.spring.auth.client.domain;

import com.spring.auth.shared.domain.Auditable;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spring_client")
public class ClientJpa extends Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    private Long clientId;
    private String clientSecret = "";
    private Date creationDate = new Date();
    private String allowedUrls = "";
    private String allowedCallbackUrls = "";
    private long expirationTimeToken = 1000 * 60 * 20; // 1s > 1m > 20m
    private String googleClientId = "";

    public ClientJpa(
            Date createdAt,
            Date lastModified,
            String createdBy,
            String lastModifiedBy,
            Long id,
            Long clientId,
            String clientSecret,
            Date creationDate,
            String allowedUrls,
            String allowedCallbackUrls,
            long expirationTimeToken,
            String googleClientId) {
        super(createdAt, lastModified, createdBy, lastModifiedBy);
        this.id = id;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.creationDate = creationDate;
        this.allowedUrls = allowedUrls;
        this.allowedCallbackUrls = allowedCallbackUrls;
        this.expirationTimeToken = expirationTimeToken;
        this.googleClientId = googleClientId;
    }
}
