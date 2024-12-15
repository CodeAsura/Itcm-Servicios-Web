package mx.cdmadero.tecnm.web_project.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity(name = "storage")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class Storage implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(id, storage.id) &&
                Objects.equals(name, storage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
