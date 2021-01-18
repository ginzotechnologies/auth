package com.spring.auth.authorization.application;


import com.spring.auth.anotations.components.UseCase;
import com.spring.auth.authorization.application.ports.AccessPort;
import com.spring.auth.authorization.application.ports.ExchangePort;
import com.spring.auth.authorization.application.ports.TokenInfoPort;
import com.spring.auth.authorization.domain.TokenInfo;
import com.spring.auth.exceptions.application.*;
import com.spring.auth.util.RegexUtil;
import com.spring.auth.util.TokenUtil;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * @author diegotobalina created on 24/06/2020
 */
@UseCase
public class ExchangeUseCase implements ExchangePort {

    private final TokenInfoPort tokenInfoPort;
    private final AccessPort accessPort;

    public ExchangeUseCase(TokenInfoPort tokenInfoPort, AccessPort accessPort) {
        this.tokenInfoPort = tokenInfoPort;
        this.accessPort = accessPort;
    }

    @Override
    public TokenUtil.JwtWrapper exchange(String token, Long clientId)
            throws NotFoundException, UnknownTokenFormatException, InvalidTokenException,
            GeneralSecurityException, IOException, GoogleGetInfoException,
            EmailDoesNotExistsException, LockedUserException, DuplicatedKeyException,
            InfiniteLoopException {
        if (!RegexUtil.isGoogleJwt(token)) { // only exchange google tokens
            throw new InvalidTokenException("this token must ve a Google token");
        }
        TokenInfo tokenInfo = tokenInfoPort.tokenInfo(token, clientId);
        Long userId = tokenInfo.getUserId();
        return accessPort.access(userId);
    }
}
