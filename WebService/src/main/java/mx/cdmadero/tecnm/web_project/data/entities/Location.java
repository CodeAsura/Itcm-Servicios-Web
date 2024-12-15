package mx.cdmadero.tecnm.web_project.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "location")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class Location implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "storage_id")
    private Storage storage;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer hallwayNumber;
    private Integer shelfNumber;
    private Integer level;
    private boolean isActive;
}