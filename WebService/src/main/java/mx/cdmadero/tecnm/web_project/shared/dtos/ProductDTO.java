package mx.cdmadero.tecnm.web_project.shared.dtos;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductDTO {
    public String upc;
    public String name;
    public String description;
    public String lastCost;
    public String lastPrice;
}
