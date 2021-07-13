package com.alchemy.transformers.impl;

import com.alchemy.dto.modelsdto.UserDto;
import com.alchemy.entities.User;
import com.alchemy.transformers.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTransformer implements Transformer<User, UserDto> {

    @Override
    public UserDto entityToDto(User entity) {
        return UserDto.anUserDto()
                .setName(entity.getName())
                .setE_mail(entity.getE_mail())
                .setPassword(entity.getPassword())
                .setCoins(entity.getCoins())
                .setIngredients(entity.getIngredients())
                .setUnlockedRecipes(entity.getUnlockedRecipes())
                .build();
    }

    @Override
    public User dtoToEntity(UserDto dto) {
        return User.anUser()
                .setName(dto.getName())
                .setE_mail(dto.getE_mail())
                .setPassword(dto.getPassword())
                .setCoins(dto.getCoins())
                .setIngredients(dto.getIngredients())
                .setUnlockedRecipes(dto.getUnlockedRecipes())
                .build();
    }
}
