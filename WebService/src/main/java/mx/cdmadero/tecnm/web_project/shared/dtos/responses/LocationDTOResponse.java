package mx.cdmadero.tecnm.web_project.shared.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mx.cdmadero.tecnm.web_project.shared.dtos.interfaces.ResponseDTO;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LocationDTOResponse implements ResponseDTO {
    public Integer id;
    public Integer storageId;
    public Integer productId;
    private Integer hallwayNumber;
    private Integer shelfNumber;
    private Integer level;
}
