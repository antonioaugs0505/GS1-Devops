package com.gs1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gs1.model.UsuarioModel;
import com.gs1.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioModel createUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> createUsuarios(List<UsuarioModel> usuarios) {
        return usuarioRepository.saveAll(usuarios);
    }

    public List<UsuarioModel> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<UsuarioModel> updateUsuario(Long id, UsuarioModel usuarioDetails) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNm_Usuario(usuarioDetails.getNm_Usuario());
            usuario.setNr_Cpf(usuarioDetails.getNr_Cpf());
            usuario.setNm_Rg(usuarioDetails.getNm_Rg());
            usuario.setDt_Nascimento(usuarioDetails.getDt_Nascimento());
            usuario.setNm_Email(usuarioDetails.getNm_Email());
            usuario.setNm_Senha(usuarioDetails.getNm_Senha());
            usuario.setDt_Cadastro(usuarioDetails.getDt_Cadastro());
            return usuarioRepository.save(usuario);
        });
    }

    public boolean deleteUsuario(Long id) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuarioRepository.delete(usuario);
            return true;
        }).orElse(false);
    }
}
