package org.example.expense_tracking.service.serviceimplement;

import org.example.expense_tracking.model.dto.request.UserRegisterRequest;
import org.example.expense_tracking.model.dto.response.UserRegisterResponse;
import org.example.expense_tracking.model.entity.CustomUserDetail;
import org.example.expense_tracking.model.entity.User;
import org.example.expense_tracking.repository.UserRepository;
import org.example.expense_tracking.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        return new CustomUserDetail(user);
    }

    @Override
    public UserRegisterResponse createNewUser(UserRegisterRequest userRegisterRequest) {
        String password = bCryptPasswordEncoder.encode(userRegisterRequest.getPassword());
        userRegisterRequest.setPassword(password);
        User user = userRepository.createNewUser(userRegisterRequest);
        return mapper.map(user, UserRegisterResponse.class);
    }
}
