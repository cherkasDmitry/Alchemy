package com.alchemy.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
@Builder(setterPrefix = "set", builderMethodName = "anRecipe", toBuilder = true)
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GenericGenerator(name = "recipe_id", strategy = "com.alchemy.utils.UUIDIdGenerator")
    @GeneratedValue(generator = "recipe_id")
    private String id;
    @Column(name = "elixir_name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_items",
            joinColumns = {@JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id")})
    private List<Ingredient> recipe;
}
