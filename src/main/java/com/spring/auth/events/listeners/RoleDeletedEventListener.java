package com.spring.auth.events.listeners;

import com.spring.auth.anotations.components.CustomEventListener;
import com.spring.auth.events.domain.RoleDeletedEvent;
import com.spring.auth.exceptions.application.DuplicatedKeyException;
import com.spring.auth.role.domain.Role;
import com.spring.auth.user.application.ports.in.RemoveRolesFromUserPort;
import com.spring.auth.user.application.ports.out.FindUserPort;
import com.spring.auth.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

/** @author diegotobalina created on 19/06/2020 */
@CustomEventListener
@AllArgsConstructor
public class RoleDeletedEventListener {

  private RemoveRolesFromUserPort removeRolesFromUserPort;
  private FindUserPort findUserPort;

  /** When a role is deleted should be removed from the users */
  @Async
  @TransactionalEventListener
  public void removeRolesFromUsers(RoleDeletedEvent roleDeletedEvent)
      throws DuplicatedKeyException {
    Role deletedRole = roleDeletedEvent.getSource();
    List<User> users = findUserPort.findAllByRoleId(deletedRole.getId());
    removeRolesFromUserPort.remove(users, List.of(deletedRole.getId()));
  }
}
