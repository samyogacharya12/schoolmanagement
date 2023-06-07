package com.example.schoolmanagement.service;

import com.example.schoolmanagement.config.UserInfoDetails;
import com.example.schoolmanagement.dto.RegisterUserDto;
import com.example.schoolmanagement.dto.UserDto;
import com.example.schoolmanagement.entity.User;
import com.example.schoolmanagement.enumconstants.UserType;
import com.example.schoolmanagement.mapper.UserMapper;
import com.example.schoolmanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {


    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private StudentService studentService;


    @Autowired
    private TeacherService teacherService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl() {

    }

    private UserServiceImpl(UserRepository userRepository,
                            UserMapper userMapper,
                            TeacherService teacherService,
                            StudentService studentService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.teacherService=teacherService;
        this.studentService=studentService;
    }

    @Override
    @Transactional
    public RegisterUserDto save(RegisterUserDto registerUserDto) {
        log.debug("saving user");
        registerUserDto.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        User user = this.userMapper.toEntity(registerUserDto);
        UserDto userDto = this.userMapper.toDto(userRepository.save(user));
        registerUserDto.setUserId(userDto.getId());
        if (userDto.getRoles().equals(UserType.ROLE_STUDENT.toString())) {
            return new RegisterUserDto(this.studentService.save(registerUserDto));
        }
        return new RegisterUserDto(this.teacherService.save(registerUserDto));
    }

    @Override
    @CachePut(cacheNames = "user", key = "#userDto.id")
    public UserDto update(UserDto userDto) {
        log.info("updating user");
        User user = this.userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }


    @Override
    @Cacheable(cacheNames = "user", key = "#id")
    public UserDto findById(Long id) {
        log.info("fetching user from database");
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return this.userMapper.toDto(user.get());
        }
        throw new UsernameNotFoundException("User not found" + id);
    }

    @Override
    @CacheEvict(cacheNames = "user", key = "#id")
    public void delete(Long id) {
        log.info("delete user");
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
