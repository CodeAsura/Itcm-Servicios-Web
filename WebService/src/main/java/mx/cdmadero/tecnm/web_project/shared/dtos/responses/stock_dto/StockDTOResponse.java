package mx.cdmadero.tecnm.web_project.shared.dtos.responses.stock_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mx.cdmadero.tecnm.web_project.shared.dtos.interfaces.ResponseDTO;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.ProductDTOResponse;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class StockDTOResponse {
    private ProductDTOResponse product;
    private int quantity;
}