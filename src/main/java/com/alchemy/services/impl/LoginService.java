package com.alchemy.services.impl;

import com.alchemy.dto.requsestdto.AuthRequest;
import com.alchemy.dto.responsedto.LoginResponse;
import com.alchemy.entities.User;
import com.alchemy.security.jwt.JwtTokenProvider;
import com.alchemy.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    private final UserService userService;

    public LoginResponse toLoginResponse(AuthRequest requestDto) {
        String username = requestDto.getUsername();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
        User user = userService.getUserTransformer().dtoToEntity(userService.getByName(username));
        String token = jwtTokenProvider.createToken(username, user.getRoles());
        return new LoginResponse(username, token);
    }
}
