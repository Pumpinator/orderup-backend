package edu.mx.utleon.orderup.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

}
