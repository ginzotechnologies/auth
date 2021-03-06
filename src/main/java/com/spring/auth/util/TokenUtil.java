package com.spring.auth.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.auth.exceptions.application.InvalidTokenException;
import com.spring.auth.role.domain.Role;
import com.spring.auth.scope.domain.Scope;
import com.spring.auth.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author diegotobalina created on 24/06/2020
 */
public abstract class TokenUtil {

    private static final String PREFIX = "Bearer ";
    private static final String GOOGLE_PREFIX = "Google ";

    private TokenUtil() {
    }

    public static String addBearerPrefix(String token) {
        if (StringUtils.startsWith(token, PREFIX)) return token;
        return PREFIX + token;
    }

    public static String addGooglePrefix(String token) {
        if (StringUtils.startsWith(token, GOOGLE_PREFIX)) return token;
        return GOOGLE_PREFIX + token;
    }

    public static String removeBearerPrefix(String token) {
        if (!StringUtils.startsWith(token, PREFIX)) return token;
        return token.replace(PREFIX, "");
    }

    public static String removeGooglePrefix(String token) {
        if (!StringUtils.startsWith(token, GOOGLE_PREFIX)) return token;
        return token.replace(GOOGLE_PREFIX, "");
    }

    public static JwtWrapper generateBearerJwt(
            User user,
            String secretKey,
            long expirationTime,
            List<String> roleValues,
            List<String> scopeValues) {
        Map<String, Object> claims = generateJwtClaims(user, roleValues, scopeValues);
        Long userId = user.getId();
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + expirationTime);
        byte[] secretKeyBytes = secretKey.getBytes();
        String jwt = generateBearerJwt(userId, claims, issuedAt, expiration, secretKeyBytes);
        List<String> scopes = scopeValues;
        List<String> roles = roleValues;
        return new JwtWrapper(jwt, issuedAt, expiration, userId, roles, scopes);
    }

    public static JwtWrapper getValues(String jwt, String secretKey) throws InvalidTokenException {
        try {
            Claims claims = getClaims(jwt, secretKey);
            Long userId = getUserId(claims);
            Date issuedAt = getIssuedAt(claims);
            Date expiration = getExpiration(claims);
            List<String> roles = getRoles(claims);
            List<String> scopes = getScopes(claims);
            return new JwtWrapper(jwt, issuedAt, expiration, userId, roles, scopes);
        } catch (Exception exception) {
            throw new InvalidTokenException("invalid jwt: " + exception.getMessage());
        }
    }

    private static Map<String, Object> generateJwtClaims(
            User user, List<String> roleValues, List<String> scopeValues) {
        Long userId = user.getId();
        var scopes =
                user.getScopes().stream()
                        .filter(
                                scope ->
                                        scopeValues == null
                                                || scopeValues.isEmpty()
                                                || scopeValues.contains(scope.getValue()))
                        .map(Scope::getValue)
                        .collect(Collectors.toList());
        var roles =
                user.getRoles().stream()
                        .filter(
                                role ->
                                        roleValues == null
                                                || roleValues.isEmpty()
                                                || roleValues.contains(role.getValue()))
                        .map(Role::getValue)
                        .collect(Collectors.toList());
        return generateJwtClaims(userId, scopes, roles);
    }

    private static Map<String, Object> generateJwtClaims(
            Long userId, List<String> scopes, List<String> roles) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", userId);
        map.put("scopes", scopes);
        map.put("roles", roles);
        return map;
    }

    private static String generateBearerJwt(
            Long userId, Map<String, Object> claims, Date issuedDate, Date expirationDate, byte[] key) {
        String jwtId = UUID.randomUUID().toString();
        return Jwts.builder()
                .setId(jwtId)
                .setSubject(userId.toString())
                .setClaims(claims)
                .setIssuedAt(issuedDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    private static Claims getClaims(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
    }

    private static List<String> getRoles(Claims claims) {
        ObjectMapper mapper = new ObjectMapper();
        Object roles = claims.get("roles");
        return mapper.convertValue(roles, new TypeReference<>() {
        });
    }

    private static List<String> getScopes(Claims claims) {
        ObjectMapper mapper = new ObjectMapper();
        Object scopes = claims.get("scopes");
        return mapper.convertValue(scopes, new TypeReference<>() {
        });
    }

    private static Long getUserId(Claims claims) {
        return Long.parseLong(claims.get("user").toString());
    }

    private static Date getIssuedAt(Claims claims) {
        return claims.getIssuedAt();
    }

    private static Date getExpiration(Claims claims) {
        return claims.getExpiration();
    }

    @Getter
    @AllArgsConstructor
    public static class JwtWrapper {
        private final String token;
        private final Date IssuedAt;
        private final Date expiration;
        private final Long userId;
        private final List<String> roles;
        private final List<String> scopes;
    }
}
