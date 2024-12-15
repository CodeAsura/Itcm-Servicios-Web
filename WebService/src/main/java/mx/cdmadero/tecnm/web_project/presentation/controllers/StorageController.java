package mx.cdmadero.tecnm.web_project.presentation.controllers;

import mx.cdmadero.tecnm.web_project.shared.dtos.StorageDTO;
import mx.cdmadero.tecnm.web_project.domain.services.StorageProductService;
import mx.cdmadero.tecnm.web_project.domain.services.StorageService;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.stock_dto.GeneralStockDTOResponse;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.stock_dto.StockDTOResponse;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.StorageDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private StorageProductService storageProductService;

    @GetMapping
    public List<StorageDTOResponse> getStorages() {
        return storageService.getAll();
    }

    @GetMapping(value = "/{storageId}")
    public StorageDTOResponse getStorageById(@PathVariable Integer storageId) {
        return storageService.getById(storageId);
    }

    @GetMapping(value = "/trash")
    public List<StorageDTOResponse> getDeletedProducts() {
        return storageService.getDeleted();
    }

    @GetMapping(value = "/stock")
    public List<GeneralStockDTOResponse> getGeneralStock() {
        return storageProductService.getExistences();
    }

    @GetMapping(value = "/{storageId}/stock")
    public List<StockDTOResponse> getStorageStock(@PathVariable Integer storageId) {
        return storageProductService.getExistencesByStorage(storageId);
    }

    @PostMapping
    public StorageDTOResponse createStorage(@RequestBody StorageDTO createStorageRequest) {
        StorageDTOResponse storageDTO = StorageDTOResponse
                .builder()
                .name(createStorageRequest.name)
                .address(createStorageRequest.address)
                .latitude(createStorageRequest.latitude)
                .longitude(createStorageRequest.longitude)
                .build();
        return storageService.create(storageDTO);
    }

    @PutMapping
    public StorageDTOResponse updateStorage(@RequestBody StorageDTOResponse storageDTO) {
        return storageService.update(storageDTO);
    }

    @PutMapping(value = "/trash/{storageId}")
    public StorageDTOResponse restoreStorage(@PathVariable Integer storageId) {
        return storageService.restore(storageId);
    }

    @DeleteMapping(value = "/{storageId}")
    public ResponseEntity<Void> deleteStorage(@PathVariable Integer storageId) {
        storageService.delete(storageId);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
