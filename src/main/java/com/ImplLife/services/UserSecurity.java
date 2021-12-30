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
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import static com.ImplLife.entity.dto.db.Role.*;

@Component
public class UserSecurity implements UserDetailsService {
    @Autowired
    private UserDAO userDao;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private User rootTst;
    @Autowired
    private UserService userService;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        userService.fill(user);
        return user;
    }

    public User findUserByGoogleId(String googleId) {
        User user = userDao.findUserByGoogleId(googleId);
        userService.fill(user);
        return user;
    }

    public User load(OidcUserRequest userRequest) {
        User user;
        String sub = userRequest.getIdToken().getClaim("sub");
        user = findUserByGoogleId(sub);
        userService.fill(user);
        return user;
    }

}
