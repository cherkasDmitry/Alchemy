package com.alchemy.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set", builderMethodName = "anUser", toBuilder = true)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GenericGenerator(name = "user_id", strategy = "com.alchemy.utils.UUIDIdGenerator")
    @GeneratedValue(generator = "user_id")
    private String id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "e_mail")
    private String e_mail;
    @Column(name = "password")
    private String password;
    @Column(name = "coins")
    private Long coins;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "inventory_item",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id")})
    private List<Ingredient> ingredients;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_owners",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private List<UserRole> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")})
    private List<Recipe> unlockedRecipes;
}
