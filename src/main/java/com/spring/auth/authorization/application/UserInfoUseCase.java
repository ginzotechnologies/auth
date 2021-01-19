package com.spring.auth.authorization.application;

import com.spring.auth.anotations.components.UseCase;
import com.spring.auth.authorization.application.ports.TokenInfoPort;
import com.spring.auth.authorization.application.ports.UserInfoPort;
import com.spring.auth.authorization.domain.TokenInfo;
import com.spring.auth.exceptions.application.*;
import com.spring.auth.user.domain.User;
import com.spring.auth.user.infrastructure.repositories.ports.FindUserPort;
import com.spring.auth.util.UserUtil;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;

/**
 * @author diegotobalina created on 24/06/2020
 */
@UseCase
@AllArgsConstructor
public class UserInfoUseCase implements UserInfoPort {

    private final FindUserPort findUserPort;
    private final TokenInfoPort tokenInfoPort;

    @Override
    public User userInfo(Principal principal) throws NotFoundException, LockedUserException {
        // get the user id from the memory
        Long userId = UserUtil.getUserIdFromPrincipal(principal);
        // find user details from the database
        User user = getUserIfNotLocked(userId);
        return user;
    }

    @Override
    public User userInfo(String token, Long clientId)
            throws NotFoundException, LockedUserException, InfiniteLoopException,
            GeneralSecurityException, InvalidTokenException, IOException, UnknownTokenFormatException,
            EmailDoesNotExistsException, GoogleGetInfoException, DuplicatedKeyException {
        TokenInfo tokenInfo = tokenInfoPort.tokenInfo(token, clientId);
        Long userId = tokenInfo.getUserId();
        User user = getUserIfNotLocked(userId);
        return user;
    }

    private User getUserIfNotLocked(Long userId) throws NotFoundException, LockedUserException {
        // find user details from the database
        User user = findUserPort.findById(userId);
        // check if the user is locked
        if (user.isLocked()) throw new LockedUserException("this user is locked");
        return user;
    }
}
