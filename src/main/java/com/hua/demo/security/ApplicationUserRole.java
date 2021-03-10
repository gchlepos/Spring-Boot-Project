package com.hua.demo.security;

import com.google.common.collect.Sets;

import java.util.Set;

public enum ApplicationUserRole {

    CITIZEN(Sets.newHashSet()),
    EMPLOYEE(Sets.newHashSet()),
    ADMIN(Sets.newHashSet());

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
