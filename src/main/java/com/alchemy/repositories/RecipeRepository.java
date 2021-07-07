package com.alchemy.repositories;

import com.alchemy.entities.Recipe;
import com.alchemy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,String> {

    List<Recipe> findByOwners(User owner);

    Optional<Recipe> getByName(String name);
}
