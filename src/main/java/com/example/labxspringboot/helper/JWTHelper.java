package com.example.labxspringboot.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.labxspringboot.constatnt.JWTUtil.*;

@Component
public class JWTHelper {
    Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public String generateAccessToken(String email, List<String> roles){
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_ACCESS_TOKEN))
                .withIssuer(ISSUER)
                .withClaim("roles",roles)
                .sign(algorithm);
    }

    public String generateRefreshToken(String email){
        return  JWT.create().withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_REFRESH_TOKEN))
                .withIssuer(ISSUER)
                .sign(algorithm);

    }

    public String extractTokenFromHeaderIfExists(String authorizationHeader){
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PRIFIX)){
            return authorizationHeader.substring(BEARER_PRIFIX.length());
        }
        return null;
    }

    public Map<String,String> getTokentsMap(String jwtAccessToken , String jwtRefreshToken) {
        Map<String,String> idToken = new HashMap<>();
        idToken.put("accessToken" ,jwtAccessToken);
        idToken.put("refreshToken" ,jwtRefreshToken);
        return idToken;
    }
}
