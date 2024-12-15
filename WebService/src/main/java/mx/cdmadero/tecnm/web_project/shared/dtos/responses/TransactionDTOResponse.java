package mx.cdmadero.tecnm.web_project.shared.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mx.cdmadero.tecnm.web_project.shared.dtos.interfaces.ResponseDTO;
import mx.cdmadero.tecnm.web_project.shared.dtos.interfaces.OriginDestiny;
import mx.cdmadero.tecnm.web_project.shared.enums.TransactionType;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Builder
public class TransactionDTOResponse implements ResponseDTO {
    private Integer id;
    private StorageDTOResponse storage;
    private ProductDTOResponse product;
    private BusinessEntityDTOResponse employee;
    private OriginDestiny originDestiny;
    private Integer quantity;
    private TransactionType type;
    private LocalDateTime createdAt;
}