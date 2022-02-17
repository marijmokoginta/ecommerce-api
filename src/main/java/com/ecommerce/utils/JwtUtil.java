package com.ecommerce.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerce.services.UserDetailImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.ecommerce.constant.SecurityConstant.*;

@Service
public class JwtUtil {

    public String generateJwtToken(UserDetailImpl userDetail) {
        String[] claim = getClaimsFromUser(userDetail);
        return JWT.create().withSubject(userDetail.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withArrayClaim(AUTHORITIES, claim)
                .sign(Algorithm.HMAC256(SECRET_KEY.getBytes()));
    }

    private String[] getClaimsFromUser(UserDetailImpl userDetail) {
        return userDetail.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toArray(String[]::new);
    }
}
