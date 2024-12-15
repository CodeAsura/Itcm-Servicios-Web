package mx.cdmadero.tecnm.web_project.shared.dtos.interfaces;

import io.swagger.v3.oas.annotations.media.Schema;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.BusinessEntityDTOResponse;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.StorageDTOResponse;

@Schema(
        oneOf = {BusinessEntityDTOResponse.class, StorageDTOResponse.class}
)
public interface OriginDestiny { }
