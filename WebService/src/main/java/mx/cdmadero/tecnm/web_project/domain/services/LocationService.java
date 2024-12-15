package mx.cdmadero.tecnm.web_project.domain.services;

import mx.cdmadero.tecnm.web_project.data.entities.Location;
import mx.cdmadero.tecnm.web_project.data.repositories.LocationRepository;
import mx.cdmadero.tecnm.web_project.data.repositories.ProductRepository;
import mx.cdmadero.tecnm.web_project.data.repositories.StorageRepository;
import mx.cdmadero.tecnm.web_project.domain.CommonOperations;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.LocationDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService extends CommonOperations<Location, LocationRepository, LocationDTOResponse> {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StorageRepository storageRepository;

    public List<LocationDTOResponse> getAllLocationsByStorage(Integer storageId) {
        return locationRepository
                .findAllByStorage(storageId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public LocationDTOResponse getLocationByStorageAndProduct(Integer storageId, Integer productId) {
        return toDTO(locationRepository.findByStorageAndProduct(storageId, productId));
    }

    @Override
    public Location toEntity(LocationDTOResponse locationDTO) {
        var storage = storageRepository
                .findById(locationDTO.getStorageId())
                .orElseThrow(() -> new IllegalArgumentException("Storage not found with id: " + locationDTO.getStorageId()));
        var product = productRepository
                .findById(locationDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + locationDTO.getProductId()));
        return Location
                .builder()
                .storage(storage)
                .product(product)
                .hallwayNumber(locationDTO.getHallwayNumber())
                .shelfNumber(locationDTO.getShelfNumber())
                .level(locationDTO.getLevel())
                .build();
    }

    @Override
    public LocationDTOResponse toDTO(Location location) {
        return LocationDTOResponse
                .builder()
                .id(location.getId())
                .storageId(location.getStorage().getId())
                .productId(location.getProduct().getId())
                .hallwayNumber(location.getHallwayNumber())
                .shelfNumber(location.getShelfNumber())
                .level(location.getLevel())
                .build();
    }
}
