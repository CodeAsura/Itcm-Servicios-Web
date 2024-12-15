package mx.cdmadero.tecnm.web_project.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.ProductDTOResponse;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.StorageDTOResponse;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class StorageProductStockDTO {
    StorageDTOResponse storage;
    ProductDTOResponse product;
    Integer quantity;
}
