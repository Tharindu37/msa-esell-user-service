package com.esell.esell_user_service.service.impl.impl;

import com.esell.esell_user_service.dto.request.RequestUserDto;
import com.esell.esell_user_service.dto.request.RequestUserLoginDeto;
import com.esell.esell_user_service.entity.User;
import com.esell.esell_user_service.exception.DuplicateEntryException;
import com.esell.esell_user_service.exception.EntryNotFoundException;
import com.esell.esell_user_service.repo.UserRepository;
import com.esell.esell_user_service.service.impl.UserService;
import com.esell.esell_user_service.util.JwtUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    @Override
    public void signup(RequestUserDto requestUserDto) {
        if (userRepository.findByEmail(requestUserDto.getEmail()) != null) {
            throw new DuplicateEntryException("User with email " + requestUserDto.getEmail() + " already exists");
        }
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(requestUserDto.getPassword()));
        newUser.setEmail(requestUserDto.getEmail());
        newUser.setUsername(requestUserDto.getUsername());
        userRepository.save(newUser);
    }

    @Override
    public String login(RequestUserLoginDeto requestUserLoginDeto) {
        User user = userRepository.findByEmail(requestUserLoginDeto.getEmail());
        if (user == null || !passwordEncoder.matches(requestUserLoginDeto.getPassword(), user.getPassword())) {
            throw new EntryNotFoundException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return token;
    }
}
