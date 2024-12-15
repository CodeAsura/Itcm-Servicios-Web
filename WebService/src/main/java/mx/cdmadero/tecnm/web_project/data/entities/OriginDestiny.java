package mx.cdmadero.tecnm.web_project.data.entities;

import jakarta.persistence.*;
import lombok.*;
import mx.cdmadero.tecnm.web_project.data.converters.OriginDestinyTypeConverter;
import mx.cdmadero.tecnm.web_project.shared.enums.OriginDestinyType;

@Entity(name = "origin_destiny")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class OriginDestiny {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne(mappedBy = "originDestiny")
    private Transaction transaction;
    @Convert(converter = OriginDestinyTypeConverter.class)
    private OriginDestinyType type; // can be provider, storage or client
    private Integer referenceId;
    private boolean isActive;
}
