package edu.mx.utleon.orderup.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "estatus")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Estatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

}
