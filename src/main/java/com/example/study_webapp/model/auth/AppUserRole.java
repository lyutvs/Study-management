package com.example.study_webapp.model.auth;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.study_webapp.model.auth.AppUserPermission.*;

public enum AppUserRole {

    USER(Sets.newHashSet(GUEST_READ, USER_READ, USER_WRITE)),
    GUEST(Sets.newHashSet(GUEST_READ));

    private final Set<AppUserPermission> permission;

    AppUserRole(Set<AppUserPermission> permission) {
        this.permission = permission;
    }

    public Set<AppUserPermission> getPermission() {
        return permission;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermission()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }

}
