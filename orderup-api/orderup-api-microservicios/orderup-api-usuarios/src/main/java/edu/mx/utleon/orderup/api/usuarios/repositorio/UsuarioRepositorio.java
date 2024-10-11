package edu.mx.utleon.orderup.api.usuarios.repositorio;

import edu.mx.utleon.orderup.modelo.entidad.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByNombre(String nombre);

}
