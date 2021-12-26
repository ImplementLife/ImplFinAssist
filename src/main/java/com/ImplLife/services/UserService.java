package com.ImplLife.services;

import com.ImplLife.entity.dto.db.Role;
import com.ImplLife.entity.dto.db.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    public User userInfo(Principal principal) {
        User user = null;
        try {
            user = (User) ((Authentication) principal).getPrincipal();
            fill(user);
        } catch (Exception e) {
            throw new RuntimeException("user not auth", e);
        }
        return user;
    }

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
