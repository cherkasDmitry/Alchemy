package com.alchemy.services;

import com.alchemy.dto.modelsdto.UserDto;
import com.alchemy.dto.requsestdto.RegistrationRequest;
import com.alchemy.entities.User;
import com.alchemy.transformers.Transformer;

import java.util.List;

public interface UserService {

    UserDto getById(String id);

    UserDto getByName(String name);

    List<UserDto> getAll();

    void delete(String id);

    void register(RegistrationRequest userDto);

    Transformer<User,UserDto> getUserTransformer();
}
