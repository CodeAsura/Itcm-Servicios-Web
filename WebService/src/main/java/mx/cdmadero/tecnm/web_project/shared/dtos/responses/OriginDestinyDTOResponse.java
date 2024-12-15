package mx.cdmadero.tecnm.web_project.shared.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mx.cdmadero.tecnm.web_project.shared.dtos.interfaces.ResponseDTO;
import mx.cdmadero.tecnm.web_project.shared.enums.OriginDestinyType;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class OriginDestinyDTOResponse implements ResponseDTO {
    Integer id;
    OriginDestinyType type;
}
