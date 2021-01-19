package com.spring.auth.user.application.ports;

import com.spring.auth.exceptions.application.DuplicatedKeyException;
import com.spring.auth.exceptions.application.NotFoundException;
import com.spring.auth.user.domain.User;

import java.util.List;

/**
 * Remove the roles from a specific user
 */
public interface RemoveRolesFromUserPort {

    /**
     * Remove the roles form the user
     */
    User remove(User user, List<Long> roleIds) throws DuplicatedKeyException;

    /**
     * Remove the roles form the user
     */
    User remove(Long userId, List<Long> roleIds) throws NotFoundException, DuplicatedKeyException;

    /**
     * Remove the roles form all the users
     */
    List<User> remove(List<Long> roleIds) throws DuplicatedKeyException;

    /**
     * Remove the role form all the users
     */
    List<User> remove(Long roleId) throws DuplicatedKeyException;

    /**
     * Remove the roles form the users
     */
    List<User> remove(List<User> users, List<Long> roleIds) throws DuplicatedKeyException;
}
