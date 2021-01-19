package com.spring.auth.user.infrastructure.repositories.jpa;

import com.spring.auth.user.domain.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author diegotobalina created on 24/06/2020 Database operations for the UserJpa
 */
@Repository
public interface UserRepositoryJpa extends JpaRepository<UserJpa, Long> {

    /**
     * Finds a user by the username
     */
    Optional<UserJpa> findByUsername(String username);

    /**
     * Finds a user by the email
     */
    Optional<UserJpa> findByEmail(String email);

    /**
     * Finds a user by the username or email
     */
    Optional<UserJpa> findByUsernameOrEmail(String username, String email);

    /**
     * Finds all the users that have the role by the roleId
     */
    List<UserJpa> findAllByRolesId(Long roleId);

    /**
     * Finds all the users that have the role by the roleId list
     */
    List<UserJpa> findAllByRolesIdIn(List<Long> roleIds);

    /**
     * Finds all the users that have the scope by the scopeId
     */
    List<UserJpa> findAllByScopesId(Long scopeId);

    /**
     * Check if the users exists in the database using the username
     */
    boolean existsByUsername(String username);

    /**
     * Check if the users exists in the database using the email
     */
    boolean existsByEmail(String email);

    /**
     * Check if the users exists in the database using the username and has not the same id
     */
    boolean existsByUsernameAndIdNot(String username, Long id);

    /**
     * Check if the users exists in the database using the email and has not the same id
     */
    boolean existsByEmailAndIdNot(String email, Long id);

    /**
     * Check if the users exists in the database using the username and has not the same id
     */
    boolean existsByUsernameInAndIdNotIn(List<String> usernames, List<Long> ids);

    /**
     * Check if the users exists in the database using the email and has not the same id
     */
    boolean existsByEmailInAndIdNotIn(List<String> emails, List<Long> ids);
}
