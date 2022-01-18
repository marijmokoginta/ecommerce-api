package com.ecommerce.services;

import com.ecommerce.models.entities.User;
import com.ecommerce.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("user with email '%s' not found", email)));
    }

    public User registrasiUser(User user) {
        boolean idExist = userRepository.findById(user.getId()).isPresent();
        boolean userExist = userRepository.findByEmail(user.getEmail()).isPresent();
        if(userExist && !idExist) {
            throw new RuntimeException(
                    String.format("user with email '%s' already exist", user.getEmail())
            );
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public Iterable<User> findAllUser() {
        return userRepository.findAll();
    }
}
