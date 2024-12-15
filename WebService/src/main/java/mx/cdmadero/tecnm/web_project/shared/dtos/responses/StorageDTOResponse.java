package mx.cdmadero.tecnm.web_project.shared.dtos.responses;

import lombok.*;
import mx.cdmadero.tecnm.web_project.data.entities.Storage;
import mx.cdmadero.tecnm.web_project.shared.dtos.interfaces.ResponseDTO;
import mx.cdmadero.tecnm.web_project.shared.dtos.interfaces.OriginDestiny;

import java.util.Objects;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Builder
public class StorageDTOResponse implements OriginDestiny, ResponseDTO {
    private Integer id;
    private String name;
    private String address;
    private String latitude;
    private String longitude;

    public StorageDTOResponse(Storage storage) {
        this.id = storage.getId();
        this.name = storage.getName();
        this.address = storage.getAddress();
        this.latitude = storage.getLatitude();
        this.longitude = storage.getLongitude();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageDTOResponse storageDTO = (StorageDTOResponse) o;
        return Objects.equals(id, storageDTO.id) &&
                Objects.equals(name, storageDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
