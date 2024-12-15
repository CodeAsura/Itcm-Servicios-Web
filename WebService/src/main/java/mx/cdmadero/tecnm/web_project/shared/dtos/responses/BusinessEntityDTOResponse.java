package mx.cdmadero.tecnm.web_project.shared.dtos.responses;

import lombok.*;
import mx.cdmadero.tecnm.web_project.data.entities.BusinessEntity;
import mx.cdmadero.tecnm.web_project.shared.dtos.interfaces.ResponseDTO;
import mx.cdmadero.tecnm.web_project.shared.dtos.interfaces.OriginDestiny;
import mx.cdmadero.tecnm.web_project.shared.enums.BusinessEntityType;

import java.sql.Date;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class BusinessEntityDTOResponse implements OriginDestiny, ResponseDTO {
    private Integer id;
    private String name;
    private String rfc;
    private String email;
    private String phone;
    private BusinessEntityType type;
    private Date created_on;

    public BusinessEntityDTOResponse(BusinessEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.rfc = entity.getRFC();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.type = entity.getType();
        this.created_on = entity.getCreated_on();
    }
}
