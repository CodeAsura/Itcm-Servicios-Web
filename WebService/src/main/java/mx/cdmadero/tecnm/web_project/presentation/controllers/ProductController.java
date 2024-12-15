package mx.cdmadero.tecnm.web_project.presentation.controllers;

import mx.cdmadero.tecnm.web_project.shared.dtos.ProductDTO;
import mx.cdmadero.tecnm.web_project.domain.services.ProductService;
import mx.cdmadero.tecnm.web_project.domain.services.StorageProductService;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.stock_dto.StockDTOResponse;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.ProductDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private StorageProductService storageProductService;

    @GetMapping
    public List<ProductDTOResponse> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping(value = "/{productId}/stock/{storageId}")
    public StockDTOResponse getStockByStorage(@PathVariable("productId") Integer productId, @PathVariable("storageId") Integer storageId) {
        return storageProductService.getExistencesByStorageAndProduct(storageId, productId);
    }

    @GetMapping(value = "/{productId}")
    public ProductDTOResponse getProductById(@PathVariable Integer productId) {
        return productService.getById(productId);
    }

    @GetMapping(value = "/trash")
    public List<ProductDTOResponse> getDeletedProducts() {
        return productService.getDeleted();
    }

    @PostMapping
    public ProductDTOResponse createProduct(@RequestBody ProductDTO createProductRequest) {
        ProductDTOResponse productDTO = ProductDTOResponse
                .builder()
                .UPC(createProductRequest.upc)
                .name(createProductRequest.name)
                .description(createProductRequest.description)
                .lastCost(createProductRequest.lastCost)
                .lastPrice(createProductRequest.lastPrice)
                .build();
        return productService.create(productDTO);
    }

    @PutMapping
    public ProductDTOResponse updateProduct(@RequestBody ProductDTOResponse productDTO) {
        return productService.update(productDTO);
    }

    @PutMapping(value = "/trash/{productId}")
    public ProductDTOResponse restoreProduct(@PathVariable Integer productId) {
        return productService.restore(productId);
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

}
