package com.alchemy.services.impl;

import com.alchemy.dto.modelsdto.IngredientDto;
import com.alchemy.dto.modelsdto.UserDto;
import com.alchemy.dto.requsestdto.RegistrationRequest;
import com.alchemy.entities.Ingredient;
import com.alchemy.entities.User;
import com.alchemy.entities.UserRole;
import com.alchemy.repositories.UserRepository;
import com.alchemy.repositories.UserRolesRepository;
import com.alchemy.services.UserService;
import com.alchemy.transformers.Transformer;
import com.alchemy.utils.AlchemyUtils;
import com.alchemy.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alchemy.utils.AlchemyConstants.SIMPLE_ALCHEMIST;
import static com.alchemy.utils.AlchemyConstants.USER_SERVICE_LOGGER_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Transformer<User, UserDto> userTransformer;
    private final Transformer<Ingredient, IngredientDto> ingredientTransformer;
    private final Transformer<RegistrationRequest, UserDto> regTransformer;
    private final UserRepository userRepository;
    private final UserRolesRepository rolesRepository;

    @Override
    public Transformer<User, UserDto> getUserTransformer() {
        return userTransformer;
    }

    @Override
    public UserDto getById(String id) {
        UserDto result = userRepository.findById(id)
                .map(userTransformer::entityToDto)
                .orElseThrow(() -> new NotFoundException(USER_SERVICE_LOGGER_NAME, "id", id));
        log.info("IN findById found - {}; for - {}", result.toString(), USER_SERVICE_LOGGER_NAME);
        return result;
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> result = userTransformer.entityToDto(userRepository.findAll());
        log.info("IN getAll - {} entities found; for - {}", result.size(), USER_SERVICE_LOGGER_NAME);
        return result;
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
        log.info("IN delete entity with id: {}; for - {}", id, USER_SERVICE_LOGGER_NAME);
    }

    @Override
    public UserDto getByName(String userName) {
        UserDto result = userRepository.getUserByName(userName)
                .map(userTransformer::entityToDto)
                .orElseThrow(() -> new NotFoundException(USER_SERVICE_LOGGER_NAME, "name", userName));
        log.info("IN getByName found - {}; for - {}", result.toString(), USER_SERVICE_LOGGER_NAME);
        return result;
    }

    @Override
    public void register(RegistrationRequest reqDto) {
        User user =
                userTransformer.dtoToEntity(
                        regTransformer.entityToDto(reqDto)
                );
        UserRole role = rolesRepository.findByName(SIMPLE_ALCHEMIST).isPresent()
                ? rolesRepository.findByName(SIMPLE_ALCHEMIST).get()
                : null;
        userRepository.saveAndFlush(AlchemyUtils.setRole(role, user));
        log.info("IN registered entity: {}; for - {}", reqDto, USER_SERVICE_LOGGER_NAME);
    }
}
