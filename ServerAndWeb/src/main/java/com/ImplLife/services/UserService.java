package com.ImplLife.services;

import com.ImplLife.dao.UserDAO;
import com.ImplLife.entity.dto.db.Role;
import com.ImplLife.entity.dto.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User userInfo(Principal principal) {
        User user = null;
        try {
            user = (User) ((Authentication) principal).getPrincipal();
            /*if (user.getUsername() != null) {
                user = userDAO.findByUsername(user.getUsername());
            } else */if (user.getGoogleId() != null) {
                user = userDAO.findUserByGoogleId(user.getGoogleId());
            }

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
        Set<Role> roles;
        if (user.getRoles() != null) {
            roles = new HashSet<>(user.getRoles());
        }
        else roles = new HashSet<>();
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
