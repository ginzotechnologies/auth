package com.spring.auth.session.infrastructure.repositories;

import com.spring.auth.exceptions.application.DuplicatedKeyException;
import com.spring.auth.exceptions.application.NotFoundException;
import com.spring.auth.session.application.ports.in.DeleteOlderSessionByUserIdPort;
import com.spring.auth.session.application.ports.out.CountAllSessionsByUserIdPort;
import com.spring.auth.session.application.ports.out.CreateSessionPort;
import com.spring.auth.session.domain.Session;
import com.spring.auth.session.domain.SessionJpa;
import com.spring.auth.session.domain.SessionMapper;
import com.spring.auth.session.infrastructure.repositories.jpa.SessionRepositoryJpa;
import com.spring.auth.user.application.ports.out.FindUserByIdPort;
import com.spring.auth.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class CreateSessionRepository implements CreateSessionPort {

  private SessionRepositoryJpa sessionRepositoryJpa;
  private FindUserByIdPort findUserByIdPort;
  private CountAllSessionsByUserIdPort countAllSessionsByUserIdPort;
  private DeleteOlderSessionByUserIdPort deleteOlderSessionByUserIdPort;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Session create(Session session) throws DuplicatedKeyException, NotFoundException {
    checkSessionConstraints(session);
    checkMaxUserSessions(session); // todo: move to event: SessionCreatedEvent
    return saveSession(session);
  }

  private Session saveSession(Session session) {
    SessionJpa sessionJpa = SessionMapper.parse(session);
    SessionJpa savedSessionJpa = sessionRepositoryJpa.save(sessionJpa);
    return SessionMapper.parse(savedSessionJpa);
  }

  private void checkMaxUserSessions(Session session) throws NotFoundException {
    // if the user can´t have more open sessions the oldest one will be deleted
    String userId = session.getUserId();
    if (!canUserHaveMoreSessions(userId)) {
      deleteOlderSessionByUserIdPort.delete(userId);
    }
  }

  private void checkSessionConstraints(Session session) throws DuplicatedKeyException {
    // check duplicated tokens
    String token = session.getToken();
    if (sessionRepositoryJpa.existsByToken(token)) {
      throw new DuplicatedKeyException("duplicated token: " + token);
    }
  }

  private boolean canUserHaveMoreSessions(String userId) throws NotFoundException {
    User user = findUserByIdPort.find(userId);
    int sessionCount = countAllSessionsByUserIdPort.countAll(userId);
    return user.canHaveMoreSessions(sessionCount);
  }
}
