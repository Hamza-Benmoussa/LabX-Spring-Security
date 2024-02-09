package com.example.labxspringboot.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.labxspringboot.helper.JWTHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static com.example.labxspringboot.constatnt.JWTUtil.AUTH_HEADER;
import static com.example.labxspringboot.constatnt.JWTUtil.SECRET_KEY;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private JWTHelper jwtHelper;

    public JWTAuthorizationFilter(JWTHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken =jwtHelper.extractTokenFromHeaderIfExists(request.getHeader(AUTH_HEADER));
        if (accessToken != null){
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT =  jwtVerifier.verify(accessToken);
            String email = decodedJWT.getSubject();
            String [] roles = decodedJWT.getClaim("roles").asArray(String.class);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for (String role : roles){
                authorities.add(new SimpleGrantedAuthority(role));

            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email , null ,authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request,response);
        }
        else {
            filterChain.doFilter(request,response);
        }
    }
}
