package com.ImplLife.services;

import com.ImplLife.dao.UserDAO;
import com.ImplLife.entity.dto.db.Role;
import com.ImplLife.entity.dto.db.User;
import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

@Component
public class UserSecurity implements UserDetailsService {
    @Autowired
    private UserDAO userDao;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private User rootTst;

    public UserSecurity() {
        rootTst = User.builder()
                .username("root")
                .password(encoder.encode("1"))
                .googleId("115998414536109201674")
                .role(new Role(1L, "ROLE_ROOT"))
                .role(new Role(2L, "ROLE_ADMIN"))
                .build();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        if (rootTst.getUsername().equals(username)) return rootTst;
        User user = userDao.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }

    public User findUserByGoogleId(String googleId) {
        if (rootTst.getGoogleId().equals(googleId)) return rootTst;
        return userDao.findUserByGoogleId(googleId);
    }
}
