package com.fichajespi.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fichajespi.entity.Usuario;
import com.fichajespi.security.entity.UsuarioPrincipal;
import com.fichajespi.service.UsuarioService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String numero) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findByNumero(numero).get();
        return UsuarioPrincipal.build(usuario);
    }
    
}
