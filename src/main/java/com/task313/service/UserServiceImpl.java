package com.task313.service;

import com.task313.model.User;
import com.task313.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(user.getRoles().stream()
                .map(role ->roleService.findByRoleName(role.getRoleName()))
                .collect(Collectors.toList()));
        userRepository.save(user);
    }

    @Override
    public User read(long id) { return userRepository.getById(id); }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(User user) {
        if (user.getPassword().isEmpty()) {
            user.setPassword(userRepository.findById(user.getId()).get().getPassword());
        } else {
            user.setPassword(user.getPassword());
        }

        if (!user.getRoles().isEmpty()) {
            user.setRoles(user.getRoles().stream()
                    .map(role ->roleService.findByRoleName(role.getRoleName()))
                    .collect(Collectors.toList()));
        } else {
            user.setRoles(userRepository.findById(user.getId()).get().getRoles());
        }
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByEmail(s);
    }
}