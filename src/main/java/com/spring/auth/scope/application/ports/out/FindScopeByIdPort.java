package com.spring.auth.scope.application.ports.out;

import com.spring.auth.exceptions.application.NotFoundException;
import com.spring.auth.scope.domain.Scope;

public interface FindScopeByIdPort {
  Scope find(String scopeId) throws NotFoundException;
}