package com.infinite.ehrSystem.auth.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthConverter
        implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        System.out.println("JWT CLAIMS = " + jwt.getClaims());

        List<String> roles = jwt.getClaimAsStringList("roles");

        System.out.println("JWT CLAIM ROLES = " + roles);

        Collection<SimpleGrantedAuthority> authorities =
                roles == null
                        ? Collections.emptyList()
                        : roles.stream()
                          .map(SimpleGrantedAuthority::new)
                          .collect(Collectors.toList());

        System.out.println("AUTHORITIES CREATED = " + authorities);

        return new JwtAuthenticationToken(
                jwt,
                authorities
        );
    }
}