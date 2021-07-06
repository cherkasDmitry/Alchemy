package com.alchemy.conrollers;

import com.alchemy.dto.modelsdto.IngredientDto;
import com.alchemy.dto.modelsdto.UserDto;
import com.alchemy.dto.requsestdto.CombineIngredientsRequest;
import com.alchemy.facade.AlchemyFacade;
import com.alchemy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.alchemy.utils.AlchemyConstants.MAGISTER_ALCHEMIST;

@RestController
@RequestMapping(value = "/alchemy/api/v1/user")
public class UserRestController {

    private final UserService userService;
    private final AlchemyFacade alchemyFacade;

    @Autowired
    public UserRestController(UserService userService, AlchemyFacade alchemyService) {
        this.userService = userService;
        this.alchemyFacade = alchemyService;
    }

    @GetMapping("/recipes")
    public List<List<IngredientDto>> getAvailableRecipes() {
        return alchemyFacade.getAvailableRecipes();
    }

    @Secured(MAGISTER_ALCHEMIST)
    @GetMapping("/list")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable(value = "id") String id) {
        return userService.getById(id);
    }

    @GetMapping("/{name}")
    public UserDto getByName(@PathVariable(value = "name") String name) {
        return userService.getByName(name);
    }

    @Secured(MAGISTER_ALCHEMIST)
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable(value = "id") String id) {
        userService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/buy/{ingredient_name}/{ingredients_number}")
    public HttpStatus buyIngredient(@PathVariable(value = "ingredient_name") String name,
                                                    @PathVariable(value = "ingredients_number") Integer number) {
        alchemyFacade.buyIngredient(name, number);
        return HttpStatus.OK;
    }

    @PutMapping("/sell/ingredient/{ingredient_name}/{ingredients_number}")
    public HttpStatus sellIngredient(@PathVariable(value = "ingredient_name") String name,
                                                     @PathVariable(value = "ingredients_number") Integer number) {
        alchemyFacade.sellIngredient(name, number);
        return HttpStatus.OK;
    }

    @PostMapping("/mix")
    public IngredientDto makeElixir(@RequestBody CombineIngredientsRequest items) {
        return alchemyFacade.makeElixir(items);
    }
}

