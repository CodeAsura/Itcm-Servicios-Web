package mx.cdmadero.tecnm.web_project.domain.services;

import mx.cdmadero.tecnm.web_project.data.entities.Product;
import mx.cdmadero.tecnm.web_project.data.repositories.ProductRepository;
import mx.cdmadero.tecnm.web_project.domain.CommonOperations;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.ProductDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends CommonOperations<Product, ProductRepository, ProductDTOResponse> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTOResponse toDTO(Product product) {
        return new ProductDTOResponse(product);
    }

    @Override
    public Product toEntity(ProductDTOResponse productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .UPC(productDTO.getUPC())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .lastCost(productDTO.getLastCost())
                .lastPrice(productDTO.getLastPrice())
                .build();
    }

}
