package com.alchemy.conrollers;

import com.alchemy.dto.requsestdto.AuthRequest;
import com.alchemy.dto.requsestdto.RegistrationRequest;
import com.alchemy.dto.responsedto.LoginResponse;
import com.alchemy.services.LoginService;
import com.alchemy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus
    public LoginResponse login(@RequestBody AuthRequest requestDto) {
        return loginService.toLoginResponse(requestDto);
    }

    @PostMapping("/register")
    @ResponseStatus
    public HttpStatus register(@RequestBody RegistrationRequest requestDto) {
        userService.register(requestDto);
        return HttpStatus.CREATED;
    }
}
