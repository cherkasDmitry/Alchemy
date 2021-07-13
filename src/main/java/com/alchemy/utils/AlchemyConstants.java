package com.alchemy.utils;

public interface AlchemyConstants {

    String USER_SERVICE_LOGGER_NAME = "UserService_logger_name";
    String INGREDIENT_SERVICE_LOGGER_NAME = "IngredientService_logger_name";
    String JWT_SERVICE_LOGGER_NAME = "JwtUserDetailsService_logger_name";
    String FACADE_LOGGER_NAME = "AlchemyFacade_logger_name";

    Long SIMPLE_ALCHEMIST_STARTER_COINS_PACK = 100L;

    String UNIMPLEMENTED = "Unimplemented method";

    String MAGISTER_ALCHEMIST = "Admin";
    String SIMPLE_ALCHEMIST = "User";

    String LOGIN_ALCHEMIST_ENDPOINT = "/api/v1/auth/login";

    String MESSAGE_NOT_FOUND = "%s with %s='%s' was not found";
}
