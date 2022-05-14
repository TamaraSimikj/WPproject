package com.wp.project.beautysalon.service;

import com.wp.project.beautysalon.model.User;
import com.wp.project.beautysalon.model.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

   // User create(String username, String password, Role role);

    User register(String username, String password, String repeatPassword, String firstName,
                  String lastName, Role role, String email,String phoneNumber);

    User edit(String username,String firstName,String lastName,String email,String phoneNumber);

    User changePassword(String username,String password,String repeatPassword);

    List<String> findAllUsernames();

    List<User> listEmployees();

    User findById(String id);

    User findByName(String name);

    List<User> listClients();

    List<User> listAll();

}
