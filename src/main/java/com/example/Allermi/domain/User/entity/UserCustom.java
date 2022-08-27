package com.example.Allermi.domain.User.entity;

import com.mysql.cj.xdevapi.Collection;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class UserCustom implements UserDetails, CredentialsContainer {
    public UserCustom(String username, String password,
                Collection allergy) {
        // ...
    }

    public UserCustom(String username, String password, boolean enabled,
                boolean accountNonExpired, boolean credentialsNonExpired,
                boolean accountNonLocked, Collection authorities) {

        // ...
    }
}
