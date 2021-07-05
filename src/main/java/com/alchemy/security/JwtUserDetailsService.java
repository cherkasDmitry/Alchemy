package com.alchemy.security;

import com.alchemy.entities.User;
import com.alchemy.security.jwt.JwtUser;
import com.alchemy.transformers.impl.JwtUserTransformer;
import com.alchemy.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.alchemy.utils.AlchemyConstants.JWT_SERVICE_LOGGER;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserTransformer().dtoToEntity(userService.getByName(s));
        JwtUser jwtUser = JwtUserTransformer.create(user);
        log.info("User with username: {} successfully loaded; for - {}", s, JWT_SERVICE_LOGGER);
        return jwtUser;
    }
}
