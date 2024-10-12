package edu.mx.utleon.orderup.api.usuarios.componente;

import edu.mx.utleon.orderup.api.usuarios.repositorio.RolRepositorio;
import edu.mx.utleon.orderup.api.usuarios.repositorio.UsuarioRepositorio;
import edu.mx.utleon.orderup.modelo.entidad.Rol;
import edu.mx.utleon.orderup.modelo.entidad.Roles;
import edu.mx.utleon.orderup.modelo.entidad.Usuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatosComponente {

    private final RolRepositorio rolRepositorio;

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepositorio usuarioRepositorio;

    @Transactional
    @EventListener
    public void crearDatos(ApplicationReadyEvent event) {
        Rol rolAdmin = crearRol(Roles.ADMIN.name());
        Rol rolHost = crearRol(Roles.HOST.name());
        Rol rolMesero = crearRol(Roles.MESERO.name());
        Rol rolCorredor = crearRol(Roles.CORREDOR.name());
        Rol rolCajero = crearRol(Roles.CAJERO.name());
        Rol rolCocinero = crearRol(Roles.COCINERO.name());

        crearUsuario(Usuario
                .builder()
                .nombre("admin")
                .clave(passwordEncoder.encode("admin"))
                .rol(rolAdmin)
                .build()
        );
    }

    private Rol crearRol(String nombre) {
        Rol rol = rolRepositorio.findByNombre(nombre).orElse(null);
        if (rol == null) {
            rol = rolRepositorio.save(Rol.builder().nombre(nombre).build());
        }
        return rol;
    }

    private Usuario crearUsuario(Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepositorio.findByNombre(usuario.getNombre()).orElse(null);
        if (nuevoUsuario == null) {
            nuevoUsuario = usuarioRepositorio.save(usuario);
        }
        return nuevoUsuario;
    }

}
