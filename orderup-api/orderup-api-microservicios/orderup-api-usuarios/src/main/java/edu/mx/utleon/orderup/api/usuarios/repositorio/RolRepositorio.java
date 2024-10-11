package edu.mx.utleon.orderup.api.usuarios.repositorio;

import edu.mx.utleon.orderup.modelo.entidad.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepositorio extends CrudRepository<Rol, Long> {

    Optional<Rol> findByNombre(String nombre);

}
