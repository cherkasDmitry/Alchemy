package com.alchemy.facades;

import com.alchemy.dto.modelsdto.IngredientDto;
import com.alchemy.entities.Ingredient;
import com.alchemy.transformers.Transformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IngredientFacade {

    private final Transformer<Ingredient, IngredientDto> ingredientTransformer;

}
