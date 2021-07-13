package com.alchemy.dto.modelsdto;

import com.alchemy.entities.IngredientType;
import com.alchemy.entities.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set", builderMethodName = "anIngredientDto", toBuilder = true)
public class IngredientDto {

    private String name;
    private IngredientType type;
    private Long cost;
    private Integer level;

}
