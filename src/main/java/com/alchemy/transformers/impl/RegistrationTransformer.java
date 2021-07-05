package com.alchemy.transformers.impl;

import com.alchemy.dto.modelsdto.UserDto;
import com.alchemy.dto.requsestdto.RegistrationRequest;
import com.alchemy.transformers.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static com.alchemy.utils.AlchemyConstants.SIMPLE_ALCHEMIST_STARTER_COINS_PACK;
import static com.alchemy.utils.AlchemyConstants.UNIMPLEMENTED;

@Component
@RequiredArgsConstructor
public class RegistrationTransformer implements Transformer<RegistrationRequest, UserDto> {

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto entityToDto(RegistrationRequest entity) {
        return UserDto.anUserDto()
                .setName(entity.getName())
                .setE_mail(entity.getE_mail())
                .setPassword(passwordEncoder.encode(entity.getPassword()))
                .setCoins(SIMPLE_ALCHEMIST_STARTER_COINS_PACK)
                .setUnlockedRecipes(null)
                .setIngredients(null)
                .build();
    }

    @Override
    public RegistrationRequest dtoToEntity(UserDto dto) {
        throw new RuntimeException(UNIMPLEMENTED);
    }
}
