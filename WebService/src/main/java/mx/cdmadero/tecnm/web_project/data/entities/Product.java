package mx.cdmadero.tecnm.web_project.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "product")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class Product implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String UPC;
    private String name;
    private String description;
    private String lastCost;
    private String lastPrice;
    private boolean isActive;


}
