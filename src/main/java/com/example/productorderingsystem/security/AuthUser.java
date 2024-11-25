package com.phegondev.phegon.productorderingsystem.security;

import com.phegondev.phegon.productorderingsystem.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.userDetails;

import java.util.collection;
import java.util.List;

@Data
@Builder
@Document(collection="auth_users")

public class AuthUser implements UserDetails{
    private User user;
    @Override

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword(){
        return user.getPassword();

    }
    @Override
    public String getUsername() {
        return user.getEmail();
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


}