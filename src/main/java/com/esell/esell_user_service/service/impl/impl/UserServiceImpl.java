package com.esell.esell_user_service.service.impl.impl;

import com.esell.esell_user_service.dto.request.RequestUserDto;
import com.esell.esell_user_service.dto.request.RequestUserLoginDeto;
import com.esell.esell_user_service.entity.User;
import com.esell.esell_user_service.exception.DuplicateEntryException;
import com.esell.esell_user_service.repo.UserRepository;
import com.esell.esell_user_service.service.impl.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
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
    public Object login(RequestUserLoginDeto requestUserLoginDeto) {
        return null;
    }
}
