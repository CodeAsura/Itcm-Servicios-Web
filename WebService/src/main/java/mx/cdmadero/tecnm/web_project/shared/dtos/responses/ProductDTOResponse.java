package mx.cdmadero.tecnm.web_project.shared.dtos.responses;

import lombok.*;
import mx.cdmadero.tecnm.web_project.data.entities.Product;
import mx.cdmadero.tecnm.web_project.shared.dtos.interfaces.ResponseDTO;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTOResponse implements ResponseDTO {
    private Integer id;
    private String UPC;
    private String name;
    private String description;
    private String lastCost;
    private String lastPrice;

    public ProductDTOResponse(Product product) {
        this.id = product.getId();
        this.UPC = product.getUPC();
        this.name = product.getName();
        this.description = product.getDescription();
        this.lastCost = product.getLastCost();
        this.lastPrice = product.getLastPrice();
    }
}
