package edu.mx.utleon.orderup.modelo.entidad;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String clave;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

}

