package mx.cdmadero.tecnm.web_project.domain.services;

import mx.cdmadero.tecnm.web_project.data.entities.StorageProduct;
import mx.cdmadero.tecnm.web_project.data.repositories.StorageProductRepository;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.stock_dto.GeneralStockDTOResponse;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.stock_dto.StockDTOResponse;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.StorageDTOResponse;
import mx.cdmadero.tecnm.web_project.shared.dtos.StorageProductStockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static mx.cdmadero.tecnm.web_project.data.specifications.StorageProductSpecifications.productIdEqualsTo;
import static mx.cdmadero.tecnm.web_project.data.specifications.StorageProductSpecifications.storageIdEqualsTo;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class StorageProductService {
    @Autowired
    private StorageProductRepository storageProductRepository;

    public List<GeneralStockDTOResponse> getExistences() {
        var res = storageProductRepository.getExistences()
                .stream()
                .collect(Collectors.groupingBy(StorageProductStockDTO::getStorage));
        return toGeneralStockDTO(res);
    }

    public List<StockDTOResponse> getExistencesByStorage(Integer storageId) {
        return storageProductRepository.getExistencesByStorage(storageId);
    }

    public StockDTOResponse getExistencesByStorageAndProduct(Integer storageId, Integer productId) {
        return storageProductRepository.getExistencesByStorageAndProduct(storageId, productId);
    }

    public void updateStockValue(Integer storageId, Integer productId, Integer amount) {
        StorageProduct productInStorage = storageProductRepository
                .findOne(
                        where(storageIdEqualsTo(storageId))
                        .and(productIdEqualsTo(productId))
                )
                .orElseThrow(() -> new IllegalArgumentException(
                        "Product not found in Storage with product id: " + productId + " and storage id:  " + storageId)
                );
        Integer newStock = productInStorage.getStock() + amount;
        productInStorage.setStock(newStock);
        storageProductRepository.save(productInStorage);
    }

    private List<GeneralStockDTOResponse> toGeneralStockDTO(Map<StorageDTOResponse, List<StorageProductStockDTO>> map) {
        return map
                .entrySet()
                .stream()
                .map((entry) ->  {
                    List<StockDTOResponse> stockDTOs = entry
                            .getValue()
                            .stream()
                            .map(sp -> new StockDTOResponse(sp.getProduct(), sp.getQuantity()))
                            .toList();
                    return new GeneralStockDTOResponse(entry.getKey(), stockDTOs);
                })
                .toList();
    }
}
