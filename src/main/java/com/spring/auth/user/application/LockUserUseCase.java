package com.spring.auth.user.application;

import com.spring.auth.anotations.components.UseCase;
import com.spring.auth.exceptions.application.DuplicatedKeyException;
import com.spring.auth.exceptions.application.NotFoundException;
import com.spring.auth.user.application.ports.LockUserPort;
import com.spring.auth.user.domain.User;
import com.spring.auth.user.infrastructure.repositories.ports.FindUserPort;
import com.spring.auth.user.infrastructure.repositories.ports.UpdateUserPort;
import lombok.AllArgsConstructor;

/**
 * @author diegotobalina created on 24/06/2020
 */
@UseCase
@AllArgsConstructor
public class LockUserUseCase implements LockUserPort {

    private final UpdateUserPort updateUserPort;
    private final FindUserPort findUserPort;

    @Override
    public User lock(Long userId) throws DuplicatedKeyException, NotFoundException {
        User user = findUserPort.findById(userId);
        return lock(user);
    }

    @Override
    public User lock(User user) throws DuplicatedKeyException {
        user.lock();
        return updateUserPort.update(user);
    }
}
