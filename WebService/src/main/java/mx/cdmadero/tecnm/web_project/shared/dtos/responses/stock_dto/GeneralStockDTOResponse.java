package mx.cdmadero.tecnm.web_project.shared.dtos.responses.stock_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.cdmadero.tecnm.web_project.shared.dtos.interfaces.ResponseDTO;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.StorageDTOResponse;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class GeneralStockDTOResponse {
    StorageDTOResponse storage;
    List<StockDTOResponse> stock;
}
