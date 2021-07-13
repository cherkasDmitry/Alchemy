package com.alchemy.services;

import com.alchemy.dto.modelsdto.UserDto;
import com.alchemy.dto.requsestdto.RegistrationRequest;
import com.alchemy.entities.User;
import com.alchemy.repositories.UserRepository;
import com.alchemy.repositories.UserRolesRepository;
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
public class UserService {

    private final Transformer<User, UserDto> userTransformer;
    private final Transformer<RegistrationRequest, UserDto> regTransformer;
    private final UserRepository userRepository;
    private final UserRolesRepository rolesRepository;

    public User getById(String id) {
        log.info("FindById; for - {}", USER_SERVICE_LOGGER_NAME);
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_SERVICE_LOGGER_NAME, "id", id));
    }

    public List<User> getAll() {
        log.info("GetAll users; for - {}", USER_SERVICE_LOGGER_NAME);
        return userRepository.findAll();
    }

    public void delete(String id) {
        userRepository.deleteById(id);
        log.info("Delete entity with id: {}; for - {}", id, USER_SERVICE_LOGGER_NAME);
    }

    public User getByName(String userName) {
        log.info("GetByName; for - {}", USER_SERVICE_LOGGER_NAME);
        return userRepository.getUserByName(userName)
                .orElseThrow(() -> new NotFoundException(USER_SERVICE_LOGGER_NAME, "name", userName));
    }

    //TODO transactional annotations
    public void register(RegistrationRequest reqDto) {
        User user =
                userTransformer.dtoToEntity(
                        regTransformer.entityToDto(reqDto)
                );
        userRepository.saveAndFlush(AlchemyUtils
                .setRole(
                        rolesRepository.findByName(SIMPLE_ALCHEMIST)
                                .orElse(null),
                        user));
        log.info("IN registered entity: {}; for - {}", reqDto, USER_SERVICE_LOGGER_NAME);
    }
}
