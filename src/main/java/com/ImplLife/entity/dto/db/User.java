package com.ImplLife.entity.dto.db;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "fa_user")
@Builder(toBuilder = true)
public class User implements UserDetails, OidcUser {
    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String googleId;
    private String username;
    private String password;
    @Transient
    private String passwordConfirm;

    @Singular
    @Transient
    private Set<Role> roles;
    private String idRoles;

    @Singular
    @OneToMany
    private List<Transaction> transactions;
    //endregion

    //region OAuth (@Transient)
    @Transient
    private Map<String, Object> attributes;
    @Transient
    private Map<String, Object> claims;
    @Transient
    private OidcUserInfo userInfo;
    @Transient
    private OidcIdToken idToken;
    @Transient
    private String name;
    //endregion

    //region Security Default
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //endregion

    //region Security OAuth2

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Map<String, Object> getClaims() {
        return claims;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public OidcIdToken getIdToken() {
        return idToken;
    }

    @Override
    public String getName() {
        return name;
    }
    //endregion
}
