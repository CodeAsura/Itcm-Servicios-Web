package mx.cdmadero.tecnm.web_project.data.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Holds the quantity of each product in each storage
 * */
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class StorageProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "storage_id")
    private Storage storage;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer stock;
    private boolean isActive;
}
