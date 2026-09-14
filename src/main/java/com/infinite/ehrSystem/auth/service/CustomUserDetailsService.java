package com.infinite.ehrSystem.auth.service;

import com.infinite.ehrSystem.auth.entity.User;
import com.infinite.ehrSystem.auth.entity.UserStatus;
import com.infinite.ehrSystem.auth.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(
            UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(
            String username)
            throws UsernameNotFoundException {

        System.out.println("LOGIN ATTEMPT : " + username);

        User user = userRepository
                .findByUsernameAndStatus(
                        username,
                        UserStatus.ACTIVE
                )
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found or account is locked"
                        )
                );

        System.out.println(
                "LOGIN SUCCESS USER : "
                        + user.getUsername()
        );

        System.out.println(
                "ROLE FOUND : "
                        + user.getRole().getName()
        );

        System.out.println(
                "DB PASSWORD : "
                        + user.getPassword()
        );

        System.out.println(
                "PASSWORD LENGTH : "
                        + user.getPassword().length()
        );

        String roleName =
                user.getRole().getName();

        return org.springframework.security.core.userdetails.User
                .withUsername(
                        user.getUsername()
                )
                .password(
                        user.getPassword()
                )
                .authorities(
                        new SimpleGrantedAuthority(
                                "ROLE_" +
                                        roleName.toUpperCase()
                        )
                )
                .accountLocked(
                        user.getStatus()
                                == UserStatus.LOCKED
                )
                .build();
    }
}