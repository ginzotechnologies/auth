package com.spring.auth.scope.infrastructure.repositories;

import com.spring.auth.events.ports.PublishScopeDeletedEventPort;
import com.spring.auth.exceptions.application.NotFoundException;
import com.spring.auth.scope.domain.Scope;
import com.spring.auth.scope.domain.ScopeJpa;
import com.spring.auth.scope.domain.ScopeMapper;
import com.spring.auth.scope.infrastructure.repositories.jpa.ScopeRepositoryJpa;
import com.spring.auth.scope.infrastructure.repositories.ports.DeleteScopePort;
import com.spring.auth.scope.infrastructure.repositories.ports.FindScopePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diegotobalina created on 24/06/2020
 */
@Repository
@AllArgsConstructor
public class DeleteScopeRepository implements DeleteScopePort {

    private final ScopeRepositoryJpa scopeRepositoryJpa;
    private final FindScopePort findScopePort;
    private final PublishScopeDeletedEventPort publishScopeDeletedEventPort;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Scope delete(Scope scope) {
        Scope deletedScope = deleteScope(scope);
        publishScopeDeletedEventPort.publish(deletedScope);
        return deletedScope;
    }

    @Override
    public Scope delete(Long scopeId) throws NotFoundException {
        Scope scope = findScopePort.findById(scopeId);
        return delete(scope);
    }

    private Scope deleteScope(Scope scope) {
        ScopeJpa scopeJpa = ScopeMapper.parse(scope);
        scopeRepositoryJpa.deleteById(scopeJpa.getId());
        return ScopeMapper.parse(scopeJpa);
    }
}
