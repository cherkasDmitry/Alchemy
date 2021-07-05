package com.alchemy.transformers.impl;

import com.alchemy.dto.modelsdto.IngredientDto;
import com.alchemy.entities.Ingredient;
import com.alchemy.transformers.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientTransformer implements Transformer<Ingredient, IngredientDto> {

    @Override
    public IngredientDto entityToDto(Ingredient entity) {
        return IngredientDto.anIngredientDto()
                .setName(entity.getName())
                .setType(entity.getType())
                .setCost(entity.getCost())
                .setLevel(entity.getLevel())
                .setIngredientOwners(entity.getIngredientOwners())
                .build();
    }

    @Override
    public Ingredient dtoToEntity(IngredientDto dto) {
        return Ingredient.anIngredient()
                .setName(dto.getName())
                .setType(dto.getType())
                .setCost(dto.getCost())
                .setLevel(dto.getLevel())
                .setIngredientOwners(dto.getIngredientOwners())
                .build();
    }
}
