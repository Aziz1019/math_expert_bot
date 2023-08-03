package dev.isakov.mathexpertai.service.impl;

import dev.isakov.mathexpertai.entity.UserCredentials;
import dev.isakov.mathexpertai.repository.UserRepository;
import dev.isakov.mathexpertai.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public Boolean save(UserCredentials user) {
        UserCredentials savedUser = userRepository.save(user);
        return savedUser.getUserId()!=0;
    }
}
