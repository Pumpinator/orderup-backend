package edu.mx.utleon.orderup.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalles_orden")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DetalleOrden {

    @EmbeddedId
    private DetalleOrdenId id;

    private Integer cantidad;

    private Double total;

    @ManyToOne
    @MapsId("orden")
    @JoinColumn(name = "orden_id")
    private Orden orden;

    @ManyToOne
    @MapsId("producto")
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Embeddable
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    public static class DetalleOrdenId {

        @Column(name = "orden_id")
        private Long orden;

        @Column(name = "producto_id")
        private Long producto;

    }

}
