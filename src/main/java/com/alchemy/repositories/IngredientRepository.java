package com.alchemy.repositories;

import com.alchemy.entities.Ingredient;
import com.alchemy.entities.IngredientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,String>  {

    Optional<Ingredient> getIngredientByName(String name);

    List<Ingredient> orderByNameAsc();

    List<Ingredient> orderByCostAsc();

    List<Ingredient> orderByLevel();

    List<Ingredient> orderByTypeAsc();

    List<Ingredient> findByName(String name);

    List<Ingredient> findByCostBefore(Long cost);

    List<Ingredient> findByCostAfter(Long cost);

    List<Ingredient> findByLevel(Integer level);

    List<Ingredient> findByType(IngredientType type);
}
