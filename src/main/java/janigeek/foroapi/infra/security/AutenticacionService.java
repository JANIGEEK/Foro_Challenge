package janigeek.foroapi.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import janigeek.foroapi.users.UsuarioRepository;

@Service
public class AutenticacionService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
       return usuarioRepository.findByUsuario(usuario);
    }

}
