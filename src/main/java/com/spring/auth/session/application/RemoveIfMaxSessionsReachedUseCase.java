package com.spring.auth.session.application;

import com.spring.auth.anotations.components.UseCase;
import com.spring.auth.exceptions.application.NotFoundException;
import com.spring.auth.session.application.ports.DeleteOlderSessionByUserIdPort;
import com.spring.auth.session.application.ports.RemoveIfMaxSessionsReachedPort;
import com.spring.auth.session.infrastructure.repositories.ports.CountSessionPort;
import com.spring.auth.user.domain.User;
import com.spring.auth.user.infrastructure.repositories.ports.FindUserPort;
import lombok.AllArgsConstructor;

/**
 * @author diegotobalina created on 24/06/2020
 */
@UseCase
@AllArgsConstructor
public class RemoveIfMaxSessionsReachedUseCase implements RemoveIfMaxSessionsReachedPort {

    private final FindUserPort findUserPort;
    private final DeleteOlderSessionByUserIdPort deleteOlderSessionByUserIdPort;
    private final CountSessionPort countSessionPort;

    @Override
    public void remove(Long userId) throws NotFoundException {
        if (!canUserHaveMoreSessions(userId)) deleteOlderSessionByUserIdPort.delete(userId);
    }

    private boolean canUserHaveMoreSessions(Long userId) throws NotFoundException {
        User user = findUserPort.findById(userId);
        int sessionCount = countSessionPort.countAllByUserId(userId);
        return user.canHaveMoreSessions(sessionCount);
    }
}
