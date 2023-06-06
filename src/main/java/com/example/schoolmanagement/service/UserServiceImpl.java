package com.example.schoolmanagement.service;

import com.example.schoolmanagement.config.UserInfoDetails;
import com.example.schoolmanagement.dto.UserDto;
import com.example.schoolmanagement.entity.User;
import com.example.schoolmanagement.enumconstants.UserType;
import com.example.schoolmanagement.mapper.UserMapper;
import com.example.schoolmanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl() {

    }

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto saver(UserDto userDto) {
        log.debug("saving user");
        if(Objects.isNull(userDto.getId())) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        User user = this.userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return this.userMapper.toDto(user.get());
        }
        throw new UsernameNotFoundException("User not found" + id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
//        Optional<User> user = userRepository.findById(id);
//        UserDetails userDetails= SecurityUtils.getCurrentUser();
//        if (user.isPresent() && user.get().getRoles().toString().equals(userDetails.getAuthorities().toString())) {
//            userRepository.deleteById(id);
//        }
    }
    @Override
    public List<UserDto> findAll() {
        log.debug("fetching user");
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    public List<UserDto> findByType(String userType) {
        log.debug("fetching user by type");
        return userMapper.toDto(userRepository.findByRoles(Enum.valueOf(UserType.class, userType)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = userRepository.findByName(username);
        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}
