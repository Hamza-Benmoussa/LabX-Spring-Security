package com.example.labxspringboot.filter;

import com.example.labxspringboot.helper.JWTHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTHelper jwtHelper;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTHelper jwtHelper) {
        this.authenticationManager = authenticationManager;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String jwtAccessToken = jwtHelper.generateAccessToken(user.getUsername(), user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        String jwtRefreshToken = jwtHelper.generateRefreshToken(user.getUsername());
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream() ,jwtHelper.getTokentsMap(jwtAccessToken,jwtRefreshToken));
    }

}
