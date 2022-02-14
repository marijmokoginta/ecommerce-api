package com.ecommerce;

import com.ecommerce.models.entities.Role;
import com.ecommerce.models.entities.UserApp;
import com.ecommerce.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

import static com.ecommerce.constant.SecurityConstant.ROLE_ADMIN;
import static com.ecommerce.constant.SecurityConstant.ROLE_USER;

@SpringBootApplication
public class EcommerceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner run(UserService userService) {
        return args -> {
            Role user = userService.save(new Role(null, ROLE_USER));
            Role admin = userService.save(new Role(null, ROLE_ADMIN));

            UserApp userApp = userService.save(new UserApp(1, "Administrator", null, "marijmokoginta21@gmail.com",
                    "123asad", null, null, null, new Date(System.currentTimeMillis()),
                    true, true));

            userApp.getRoles().add(admin);
        };
    }

}
