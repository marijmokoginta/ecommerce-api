package com.ecommerce.services;

import com.ecommerce.models.entities.Role;
import com.ecommerce.models.entities.UserApp;
import com.ecommerce.models.repositories.RoleRepository;
import com.ecommerce.models.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.ecommerce.constant.SecurityConstant.ROLE_USER;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserApp user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return UserDetailImpl.build(user);
    }

    public UserApp save(UserApp userApp) {
        boolean idExist = userRepository.existsById(userApp.getId());
        boolean userExist = userRepository.existsByEmail(userApp.getEmail());
        if(userExist && !idExist) {
            throw new RuntimeException(
                    String.format("user with email '%s' already exist", userApp.getEmail())
            );
        }
        Role role = roleRepository.findByName(ROLE_USER);
        userApp.getRoles().add(role);
        userApp.setEnabled(confirmAccount());

        String encodedPassword = bCryptPasswordEncoder.encode(userApp.getPassword());
        userApp.setPassword(encodedPassword);
        return userRepository.save(userApp);
    }

    public Iterable<UserApp> findAllUser() {
        return userRepository.findAll();
    }

    public boolean emailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean confirmAccount() {
        return true;
    }
}
