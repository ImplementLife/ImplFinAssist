package com.ImplLife.config;

import com.ImplLife.services.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class HTTP extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserSecurity userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(a -> a
                        .antMatchers("/root").hasRole("ROOT")
                        .antMatchers("/user").hasRole("USER")
                        .antMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(e -> e
                        .accessDeniedPage("/ad")
                )
                .csrf(AbstractHttpConfigurer::disable)
                .logout(l -> l
                        .logoutSuccessUrl("/login").permitAll()
                )
                .formLogin(fl -> fl
                        .loginPage("/login")
                        .loginProcessingUrl("/j_spring_security_check")
                        .failureUrl("/login?error")
                        .usernameParameter("j_username")
                        .passwordParameter("j_password")
                        .permitAll()
                )
                .oauth2Login(o -> o
                        .loginPage("/login")
                )
        ;
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

    private OidcUserService oidcUserService;

    @Bean
    public OidcUserService oidcUserService(@Autowired UserSecurity userService) {
        if (oidcUserService == null) {
            oidcUserService = new OidcUserService() {
                @Override
                public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
                    String sub = userRequest.getIdToken().getClaim("sub");
                    return userService.findUserByGoogleId(sub);
                }
            };
        }
        return oidcUserService;
    }
}
