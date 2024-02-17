package com.example.labxspringboot.security;

import com.example.labxspringboot.entity.Utilisateur;
import com.example.labxspringboot.service.impl.UtilisateurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UtilisateurServiceImpl utilisateurService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurService.loadUserByEmail(email);
        if (utilisateur == null) throw new UsernameNotFoundException("NOT FOUND USER");
        GrantedAuthority authority = new SimpleGrantedAuthority(utilisateur.getRole().name());
        return new User(utilisateur.getEmail(),utilisateur.getMotDePasse(), Collections.singleton(authority));

    }
}
