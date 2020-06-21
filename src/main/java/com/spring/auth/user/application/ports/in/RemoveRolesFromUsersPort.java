package com.spring.auth.user.application.ports.in;

import com.spring.auth.exceptions.application.DuplicatedKeyException;
import com.spring.auth.user.domain.User;

import java.util.List;

/** Remove the roles from all the users that has them */
public interface RemoveRolesFromUsersPort {
  List<User> remove(List<String> roleIds) throws DuplicatedKeyException;
}