package com.polarbookshop.edgeservice.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("user")
    public Mono<User> getUser(@AuthenticationPrincipal OidcUser oidcUser) {
        log.info("Getting details of user {}", oidcUser.getPreferredUsername());
        var user = new User(
                oidcUser.getPreferredUsername(),
                oidcUser.getGivenName(),
                oidcUser.getFamilyName(),
                oidcUser.getClaimAsStringList("roles")
        );
        return Mono.just(user);
    }

}