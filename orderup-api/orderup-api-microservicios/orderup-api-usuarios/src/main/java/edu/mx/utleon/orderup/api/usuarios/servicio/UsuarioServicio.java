package edu.mx.utleon.orderup.api.usuarios.servicio;

import edu.mx.utleon.orderup.api.usuarios.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServicio implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepositorio.findByNombre(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));
    }

    private final UsuarioRepositorio usuarioRepositorio;

}
