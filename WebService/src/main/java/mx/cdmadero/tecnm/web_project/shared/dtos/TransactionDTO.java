package mx.cdmadero.tecnm.web_project.shared.dtos;

import mx.cdmadero.tecnm.web_project.shared.dtos.responses.OriginDestinyDTOResponse;
import mx.cdmadero.tecnm.web_project.shared.enums.TransactionType;

public class TransactionDTO {
    public Integer storageId;
    public Integer productId;
    public Integer employeeId;
    public Integer quantity;
    public OriginDestinyDTOResponse originDestiny;
    public TransactionType type;
}