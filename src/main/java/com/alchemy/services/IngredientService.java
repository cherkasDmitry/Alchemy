package com.alchemy.services;

import com.alchemy.dto.modelsdto.IngredientDto;
import com.alchemy.utils.AlchemySortParams;

import java.util.List;

public interface IngredientService {

    IngredientDto getById(String id);

    IngredientDto getByName(String name);

    List<IngredientDto> getAll();

    void delete(String id);

    List<IngredientDto> sortBy(AlchemySortParams parameter);

    List<IngredientDto> filterBy(Object parameter);

    List<IngredientDto> filterByCost(Long cost, Boolean moreThanCost);
}