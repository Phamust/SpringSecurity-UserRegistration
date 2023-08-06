package com.example.demo.appuser.enums;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.appuser.enums.AppUserPermission.*;

public enum AppUserRole {
    ADMIN(Set.of(
            ADMIN_READ,
            ADMIN_UPDATE,
            ADMIN_CREATE,
            ADMIN_DELETE
    )),
    USER(Set.of(
            USER_READ,
            USER_UPDATE,
            USER_CREATE,
            USER_DELETE

    ));
    @Getter
    private final Set<AppUserPermission> appUserPermissions;
     AppUserRole(Set<AppUserPermission> permissions){
         this.appUserPermissions = permissions;
     }

     public Set<SimpleGrantedAuthority> getAuthorities(){
            Set<SimpleGrantedAuthority>authorities = getAppUserPermissions()
                    .stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
            authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
            return authorities;
     }


}
