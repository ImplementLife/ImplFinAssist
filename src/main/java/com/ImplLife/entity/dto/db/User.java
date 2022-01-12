package com.ImplLife.entity.dto.db;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "fa_user")
public class User implements UserDetails, OidcUser {
    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String googleId;
    private String username;
    private String email;
    private String password;
    @Transient
    private String passwordConfirm;

    @Transient
    private Set<Role> roles;
    private String idRoles;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Transaction> transactions;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Category> categories;

    @OneToOne
    private Category lastSelectedCategory;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<People> peoples;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Group> groups;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Requisition> requisitions;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Receipt> receipts;

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
        if (getEmail() != null) return email;
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
