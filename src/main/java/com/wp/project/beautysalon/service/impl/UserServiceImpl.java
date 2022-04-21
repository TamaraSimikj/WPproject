package com.wp.project.beautysalon.service.impl;

import com.wp.project.beautysalon.model.User;
import com.wp.project.beautysalon.model.Role;
import com.wp.project.beautysalon.model.exceptions.InvalidUsernameOrPasswordException;
import com.wp.project.beautysalon.model.exceptions.PasswordNotMatchException;
import com.wp.project.beautysalon.model.exceptions.UsernameAlreadyExistsException;
import com.wp.project.beautysalon.repository.UserRepository;
import com.wp.project.beautysalon.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return user;
    }


    @Override
    public User register(String username, String password, String repeatPassword, String firstName, String lastName,
                         Role role, String email, String phoneNumber) {
        if(username == null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidUsernameOrPasswordException();
        }
        if(!password.equals(repeatPassword))
            throw new PasswordNotMatchException();

        if(this.userRepository.findById(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username, passwordEncoder.encode(password), firstName, lastName, role, email, phoneNumber);

        return userRepository.save(user);
    }

    @Override
    public User edit(String username, String firstName, String lastName, String email, String phoneNumber) {
        User user = (User) loadUserByUsername(username);
        user.setName(firstName);
        user.setSurname(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
        return user;
    }

    @Override
    public User changePassword(String username, String password, String repeatPassword) {
        if(password!=repeatPassword)
        {
            throw new PasswordNotMatchException();
        }
        User user = (User) loadUserByUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }

    @Override
    public List<String> findAllUsernames() {
        return userRepository.findAll().stream().map(x->x.getUsername()).collect(Collectors.toList());
    }

    @Override
    public List<User> listEmployees() {
        return userRepository.findAll().stream().filter(x->x.getRole().equals(Role.ROLE_EMPLOYEE)).collect(Collectors.toList());
    }
}
