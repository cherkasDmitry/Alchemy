package com.alchemy.conrollers;

import com.alchemy.dto.requsestdto.AuthRequest;
import com.alchemy.dto.requsestdto.RegistrationRequest;
import com.alchemy.dto.responsedto.LoginResponse;
import com.alchemy.services.UserService;
import com.alchemy.services.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/alchemy/api/v1/auth")
public class AuthRestController {

    private final UserService userService;
    private final LoginService loginService;

    @Autowired
    public AuthRestController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody AuthRequest requestDto) {
        return loginService.toLoginResponse(requestDto);
    }

    @PostMapping("/register")
    public HttpStatus register(@RequestBody RegistrationRequest requestDto) {
        userService.register(requestDto);
        return HttpStatus.CREATED;
    }
}
