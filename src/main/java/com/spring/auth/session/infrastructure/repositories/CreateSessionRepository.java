package com.spring.auth.session.infrastructure.repositories;

import com.spring.auth.events.ports.PublishSessionCreatedEventPort;
import com.spring.auth.exceptions.application.DuplicatedKeyException;
import com.spring.auth.session.domain.Session;
import com.spring.auth.session.domain.SessionJpa;
import com.spring.auth.session.domain.SessionMapper;
import com.spring.auth.session.infrastructure.repositories.jpa.SessionRepositoryJpa;
import com.spring.auth.session.infrastructure.repositories.ports.CreateSessionPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diegotobalina created on 24/06/2020
 */
@Repository
@AllArgsConstructor
public class CreateSessionRepository implements CreateSessionPort {

    private final SessionRepositoryJpa sessionRepositoryJpa;
    private final PublishSessionCreatedEventPort publishSessionCreatedEventPort;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Session create(Session session) throws DuplicatedKeyException {
        checkSessionConstraints(session);
        Session createdSession = saveSession(session);
        publishSessionCreatedEventPort.publish(session);
        return createdSession;
    }

    private Session saveSession(Session session) {
        SessionJpa sessionJpa = SessionMapper.parse(session);
        SessionJpa savedSessionJpa = sessionRepositoryJpa.save(sessionJpa);
        return SessionMapper.parse(savedSessionJpa);
    }

    private void checkSessionConstraints(Session session) throws DuplicatedKeyException {
        // check duplicated tokens
        String token = session.getToken();
        if (sessionRepositoryJpa.existsByToken(token)) {
            throw new DuplicatedKeyException("duplicated token: " + token);
        }
    }
}
