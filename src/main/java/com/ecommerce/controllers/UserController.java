package com.ecommerce.controllers;

import com.ecommerce.dto.*;
import com.ecommerce.models.entities.UserApp;
import com.ecommerce.services.UserDetailImpl;
import com.ecommerce.services.UserService;
import com.ecommerce.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseData<UserApp>> register(@RequestBody UserRegistrationsDTO userRegistrationsDTO) {
        ResponseData<UserApp> responseData = new ResponseData<>();
        UserApp userApp = modelMapper.map(userRegistrationsDTO, UserApp.class);

        responseData.setStatus(true);
        responseData.getMessages().add("user saved");
        responseData.setPayload(userService.save(userApp));

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/register").toUriString());
        return ResponseEntity.created(uri).body(responseData);
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<?> signin (@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        String jwtToken =  jwtUtil.generateJwtToken(userDetail);
        List<String> roles = userDetail.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return ResponseEntity.ok(new LoginResponse(
                jwtToken, userDetail.getId(),
                userDetail.getUsername(),
                roles
        ));
    }

    @GetMapping(value = "/alluser")
    public Iterable<UserApp> showAllUser() {
        return userService.findAllUser();
    }

    @PostMapping("/emailExist")
    public boolean emailExist(@RequestBody SearchDTO searchDTO) {
        return userService.emailExist(searchDTO.getSearchKey());
    }
}
