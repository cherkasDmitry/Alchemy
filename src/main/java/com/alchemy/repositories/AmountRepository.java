package com.alchemy.repositories;

import com.alchemy.entities.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmountRepository extends JpaRepository<InventoryItem,String> {

    List<InventoryItem> findByUserId(String id);

    List<InventoryItem> findByIngredientId(String id);

    InventoryItem findByUserIdAndIngredientId(String userId, String ingredientId);
}
