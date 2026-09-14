package com.infinite.ehrSystem.auth.config;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

@Configuration
public class RegisteredClientConfig {

    @Bean
    public RegisteredClientRepository registeredClientRepository() {

        RegisteredClient ehrClient =
                RegisteredClient.withId(UUID.randomUUID().toString())

                        .clientId("ehr-client")

                        .clientSecret("$2a$10$ZSl0NN9XtWiJKfjTwes5nOni7suZqifodlKOAOV8w/OYQYet1hAwS")

                        .clientAuthenticationMethod(
                                ClientAuthenticationMethod.CLIENT_SECRET_BASIC
                        )

                        .clientAuthenticationMethod(
                                ClientAuthenticationMethod.CLIENT_SECRET_POST
                        )

                        .authorizationGrantType(
                                AuthorizationGrantType.AUTHORIZATION_CODE
                        )

                        .authorizationGrantType(
                                AuthorizationGrantType.REFRESH_TOKEN
                        )

                        .redirectUri(
                                "http://127.0.0.1:8080/login/oauth2/code/ehr-client"
                        )

                        .scope(OidcScopes.OPENID)

                        .scope(OidcScopes.PROFILE)

                        .scope("read")

                        .scope("write")

                        .clientSettings(
                                ClientSettings.builder()
                                        .requireAuthorizationConsent(true)
                                        .build()
                        )

                        .build();

        System.out.println("CLIENT REGISTERED");
        System.out.println("CLIENT ID = " + ehrClient.getClientId());
        System.out.println("AUTH METHODS = " + ehrClient.getClientAuthenticationMethods());
        System.out.println("GRANT TYPES = " + ehrClient.getAuthorizationGrantTypes());
        System.out.println("REDIRECT URIS = " + ehrClient.getRedirectUris());

        return new InMemoryRegisteredClientRepository(ehrClient);
    }
}