package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.NormeDto;
import com.example.labxspringboot.dto.UtilisateurDto;
import com.example.labxspringboot.entity.Norme;
import com.example.labxspringboot.entity.Utilisateur;
import com.example.labxspringboot.entity.enume.RoleUser;
import com.example.labxspringboot.exception.exept.EmailDejaExisteException;
import com.example.labxspringboot.exception.exept.UtilisateurFoundException;
import com.example.labxspringboot.repository.IUtilisateurRepository;
import com.example.labxspringboot.service.IUtilisateurService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl implements IUtilisateurService {

    @Autowired
    private IUtilisateurRepository utilisateurRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UtilisateurDto saveUtilisateur(UtilisateurDto utilisateurDto) {
        String encodedPassword = passwordEncoder.encode(utilisateurDto.getMotDePasse());
        utilisateurDto.setMotDePasse(encodedPassword);
        checkExistEmail(utilisateurDto);
        Utilisateur utilisateur=modelMapper.map(utilisateurDto,Utilisateur.class);
        Utilisateur savedUtilisateur=utilisateurRepository.save(utilisateur);
        return maskPasswordInDto(modelMapper.map(savedUtilisateur,UtilisateurDto.class));
    }

    @Override
    public Utilisateur loadUserByEmail(String email) {
        return utilisateurRepository.findByEmailAndDeletedFalse(email);
    }

    @Override
    public List<UtilisateurDto> getUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findByDeletedFalse();
        return utilisateurs.stream()
                .map(utilisateur -> maskPasswordInDto(modelMapper.map(utilisateur, UtilisateurDto.class)))
                .collect(Collectors.toList());
    }
    @Override
    public UtilisateurDto getUtilisateurById(Long id) throws UtilisateurFoundException {
        return utilisateurRepository.findByIdAndDeletedFalse(id)
                .map(utilisateur-> maskPasswordInDto(modelMapper.map(utilisateur,UtilisateurDto.class)))
                .orElseThrow(() -> new UtilisateurFoundException("Utilisateur Not found" + id));
    }

    private UtilisateurDto maskPasswordInDto(UtilisateurDto utilisateurDto) {
        if (utilisateurDto != null) {
            utilisateurDto.setMotDePasse("****");
        }
        return utilisateurDto;
    }

    private UtilisateurDto checkExistEmail (UtilisateurDto utilisateurDto){
        if (utilisateurDto.getEmail().equals(getByEmail(utilisateurDto.getEmail()))){
            throw new EmailDejaExisteException("deja exist cette email");
        }
        return utilisateurDto;
    }
    public String getByEmail(String email)
    {
        Utilisateur utilisateur = utilisateurRepository.findByEmailAndDeletedFalse(email);
        if(utilisateur != null)
        {
            return utilisateur.getEmail();
        }
        return null;
    }
    @Override
    public UtilisateurDto updateUtilisateur(UtilisateurDto utilisateurDto,Long id) {
        Utilisateur existingUser = utilisateurRepository.findByIdAndDeletedFalse(id).orElse(null);
        checkExistEmail(utilisateurDto);
        existingUser.setEmail(utilisateurDto.getEmail());
        existingUser.setNom((utilisateurDto.getNom()));
        existingUser.setRole(utilisateurDto.getRole());
        existingUser.setRole(RoleUser.RESPONSABLE_LABORATOIRE);
        existingUser.setDeleted(false);
        existingUser.setMotDePasse(utilisateurDto.getMotDePasse());
        Utilisateur updateUtilisateur=utilisateurRepository.save(existingUser);
        updateUtilisateur.setId(id);
        return modelMapper.map(updateUtilisateur,UtilisateurDto.class);
    }

    @Override
    public void deleteUtilisateur(Long id) {
        Utilisateur utilisateur=utilisateurRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (utilisateur!= null){
            utilisateur.setDeleted(true);
            utilisateurRepository.save(utilisateur);
        }
    }

}
