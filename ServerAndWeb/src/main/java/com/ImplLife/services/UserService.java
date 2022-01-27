package com.ImplLife.services;

import com.ImplLife.entity.dto.db.Role;
import com.ImplLife.entity.dto.db.User;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserService {

    public void fill(User user) {
        fillRoles(user);
    }

    private void fillRoles(User user) {
        Set<Role> roles = new HashSet<>(user.getRoles());
        String idRoles = user.getIdRoles();
        if (idRoles != null) {
            for (char id : idRoles.toCharArray()) {
                roles.add(Role.getById(id));
            }
        }

        StringBuilder rolesIdStringBuilder = new StringBuilder();
        for (Role role : roles) {
            rolesIdStringBuilder.append(role.getId());
        }
        user.setRoles(roles);
        user.setIdRoles(rolesIdStringBuilder.toString());
    }
}
