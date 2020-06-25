package com.spring.auth.user.application.ports.out;

import com.spring.auth.user.domain.User;

/** @author diegotobalina created on 24/06/2020 */
/** Load the user sessions from the database */
public interface LoadUserSessionsPort {
  User load(User user);
}
