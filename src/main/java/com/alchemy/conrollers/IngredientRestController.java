package com.alchemy.conrollers;

import com.alchemy.dto.modelsdto.IngredientDto;
import com.alchemy.dto.requsestdto.IngredientsFilterRequest;
import com.alchemy.services.IngredientService;
import com.alchemy.utils.AlchemySortParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/alchemy/api/v1/ingredient")
public class IngredientRestController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientRestController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/list")
    public List<IngredientDto> getAll() {
        return ingredientService.getAll();
    }

    @GetMapping("/{id}")
    public IngredientDto getById(@PathVariable(value = "id") String id) {
        return ingredientService.getById(id);
    }

    @GetMapping("/{name}")
    public IngredientDto getByName(@PathVariable(value = "name") String name) {
        return ingredientService.getByName(name);
    }

    @GetMapping("/sort/{parameter}")
    public List<IngredientDto> sortBy(@PathVariable(value = "parameter") AlchemySortParams parameter) {
        if (parameter == null) parameter = AlchemySortParams.NAME;
        return ingredientService.sortBy(parameter);
    }

    @PostMapping("/filter")
    public List<IngredientDto> filterBy(
            @RequestBody IngredientsFilterRequest parameter) {
        List<IngredientDto> result;
        if (parameter.getName() != null) result = ingredientService.filterBy(parameter.getName());
        else if (parameter.getType() != null) result = ingredientService.filterBy(parameter.getType());
        else if (parameter.getCost() != null && parameter.getMoreThanCost() != null)
            result = ingredientService.filterByCost(parameter.getCost(), parameter.getMoreThanCost());
        else throw new RuntimeException("Filtering ingredients by parameter: " + parameter + "; ERROR");
        return result;
    }
}

