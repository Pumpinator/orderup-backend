package edu.mx.utleon.orderup.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mesas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mesero_id")
    private Usuario mesero;

    @ManyToOne
    @JoinColumn(name = "corredor_id")
    private Usuario corredor;

    @ManyToOne
    @JoinColumn(name = "estatus_id")
    private Estatus estatus;

}
