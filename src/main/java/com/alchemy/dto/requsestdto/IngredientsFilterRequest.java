package com.alchemy.dto.requsestdto;

import com.alchemy.entities.IngredientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsFilterRequest {

    private String name;
    private IngredientType type;
    private Long cost;
    private Boolean moreThanCost;
}
