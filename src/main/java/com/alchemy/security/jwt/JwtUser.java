package com.alchemy.security.jwt;

import com.alchemy.entities.Ingredient;
import com.alchemy.entities.Recipe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtUser implements UserDetails {

    private final String id;
    private final String name;
    private final String e_mail;
    private final String password;
    private final Long coins;
    private final List<Recipe> unlockedRecipes;
    private final List<Ingredient> ingredients;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(String id, String name, String e_mail, String password, Long coins, List<Recipe> unlockedRecipes,
                   List<Ingredient> ingredients, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.e_mail = e_mail;
        this.password = password;
        this.coins = coins;
        this.unlockedRecipes = unlockedRecipes;
        this.ingredients = ingredients;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return e_mail;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    public Long getCoins() {
        return coins;
    }

    public List<Recipe> getUnlockedRecipes() {
        return unlockedRecipes;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
