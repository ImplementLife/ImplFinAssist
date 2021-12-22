package com.ImplLife.entity.dto.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum Role implements GrantedAuthority {
    NONE('N', "ROLE_NONE"),
    USER('U', "ROLE_USER"),
    ADMIN('A', "ROLE_ADMIN")
    ;

    private final char id;
    private final String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public static Role getById(char id) {
        if (NONE.id == id) return NONE;
        if (USER.id == id) return USER;
        if (ADMIN.id == id) return ADMIN;
        throw new IllegalArgumentException("Not exist role with id=" + id);
    }
}
