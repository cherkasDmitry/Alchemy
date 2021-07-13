package com.alchemy.services;

import com.alchemy.entities.Ingredient;
import com.alchemy.repositories.IngredientRepository;
import com.alchemy.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alchemy.utils.AlchemyConstants.INGREDIENT_SERVICE_LOGGER_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public Ingredient getById(String id) {
        log.info("FindById; for - {}", INGREDIENT_SERVICE_LOGGER_NAME);
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(INGREDIENT_SERVICE_LOGGER_NAME, id));
    }

    public Ingredient getByName(String ingredientName) {
        log.info("GetByName; for - {}", INGREDIENT_SERVICE_LOGGER_NAME);
        return ingredientRepository.getIngredientByName(ingredientName)
                .orElseThrow(() -> new NotFoundException(INGREDIENT_SERVICE_LOGGER_NAME, ingredientName));
    }

    public List<Ingredient> getAll() {
        log.info("GetAll ingredients; for - {}", INGREDIENT_SERVICE_LOGGER_NAME);
        return ingredientRepository.findAll();
    }

    public List<Ingredient> getAll(Ingredient ingredientEx) {
        log.info("GetAll filtering by {}; for - {}", ingredientEx.toString(), INGREDIENT_SERVICE_LOGGER_NAME);
        return ingredientRepository.findAll(
                Example.of(ingredientEx, ExampleMatcher.matchingAll()));
    }

    public List<Ingredient> getAll(String sortParam) {
        log.info("GetAll sorting by {}; for - {}", sortParam, INGREDIENT_SERVICE_LOGGER_NAME);
        return ingredientRepository.findAll(
                Sort.by(Sort.Direction.ASC, sortParam));
    }

    public List<Ingredient> getAll(Ingredient ingredientEx, String sortParam) {
        log.info("GetAll filtering by {} and sorting by {}; for - {}",
                ingredientEx.toString(), sortParam, INGREDIENT_SERVICE_LOGGER_NAME);
        return ingredientRepository.findAll(
                Example
                        .of(ingredientEx, ExampleMatcher.matchingAll()),
                Sort
                        .by(Sort.Direction.ASC, sortParam));
    }
}
