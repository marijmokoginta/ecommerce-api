package com.ecommerce.controllers;

import com.ecommerce.dto.ResponseData;
import com.ecommerce.dto.UserDTO;
import com.ecommerce.models.entities.User;
import com.ecommerce.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/registrasi")
    public ResponseEntity<ResponseData<User>> register(@RequestBody UserDTO userDTO) {
        ResponseData<User> responseData = new ResponseData<>();
        User user = modelMapper.map(userDTO, User.class);
        responseData.setPayload(userService.registrasiUser(user));
        responseData.setStatus(true);
        responseData.getMessages().add("user saved");
        return ResponseEntity.ok(responseData);
    }

    @GetMapping(value = "/alluser")
    public Iterable<User> showAllUser() {
        return userService.findAllUser();
    }
}
