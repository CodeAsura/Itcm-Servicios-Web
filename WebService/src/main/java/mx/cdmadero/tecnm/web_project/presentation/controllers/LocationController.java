package mx.cdmadero.tecnm.web_project.presentation.controllers;

import mx.cdmadero.tecnm.web_project.domain.services.LocationService;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.LocationDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<LocationDTOResponse> getAll() {
        return locationService.getAll();
    }

    @GetMapping(value = "/{locationId}")
    public LocationDTOResponse getLocationById(@PathVariable Integer locationId) {
        return locationService.getById(locationId);
    }

    @GetMapping(value = "/storage/{storageId}")
    public List<LocationDTOResponse> getAllLocationsInStorage(@PathVariable Integer storageId) {
        return locationService.getAllLocationsByStorage(storageId);
    }

    @GetMapping(value = "/storage/{storageId}/product/{productId}")
    public LocationDTOResponse getLocationOfProductInStorage(
            @PathVariable("storageId") Integer storageId,
            @PathVariable("productId") Integer productId
    ) {
        return locationService.getLocationByStorageAndProduct(storageId, productId);
    }

    @GetMapping(value = "/trash")
    public List<LocationDTOResponse> getDeletedLocations() {
        return locationService.getDeleted();
    }

    @PostMapping
    public LocationDTOResponse createLocation(@RequestBody LocationDTOResponse locationDTO) {
        return locationService.create(locationDTO);
    }

    @PutMapping
    public LocationDTOResponse updateLocation(@RequestBody LocationDTOResponse locationDTO) {
        return locationService.update(locationDTO);
    }

    @PutMapping(value = "/trash/{locationId}")
    public LocationDTOResponse restoreLocation(@PathVariable Integer locationId) {
        return locationService.restore(locationId);
    }

    @DeleteMapping(value = "/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Integer locationId) {
        locationService.delete(locationId);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
