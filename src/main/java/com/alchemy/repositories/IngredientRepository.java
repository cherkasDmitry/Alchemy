package com.alchemy.repositories;

import com.alchemy.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,String>  {

    Optional<Ingredient> getIngredientByName(String name);
}
