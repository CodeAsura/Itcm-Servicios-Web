package mx.cdmadero.tecnm.web_project.presentation.controllers.business_entity;

import mx.cdmadero.tecnm.web_project.shared.dtos.BusinessEntityDTO;
import mx.cdmadero.tecnm.web_project.domain.services.BusinessEntityService;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.BusinessEntityDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

public class BusinessEntityController {
    @Autowired
    protected BusinessEntityService businessEntityService;

    @GetMapping(value = "/{businessEntityId}")
    public BusinessEntityDTOResponse getBusinessEntityById(@PathVariable Integer businessEntityId) {
        return businessEntityService.getById(businessEntityId);
    }

    @PostMapping
    public BusinessEntityDTOResponse createBusinessEntity(@RequestBody BusinessEntityDTO createBusinessEntityRequest) throws ParseException {
        BusinessEntityDTOResponse businessEntityDTO = BusinessEntityDTOResponse
                .builder()
                .rfc(createBusinessEntityRequest.rfc)
                .name(createBusinessEntityRequest.name)
                .email(createBusinessEntityRequest.email)
                .phone(createBusinessEntityRequest.phone)
                .type(createBusinessEntityRequest.type)
                .created_on(createBusinessEntityRequest.created_on)
                .build();
        return businessEntityService.create(businessEntityDTO);
    }

    @PutMapping
    public BusinessEntityDTOResponse updateBusinessEntity(@RequestBody BusinessEntityDTOResponse businessEntityDTO) {
        return businessEntityService.update(businessEntityDTO);
    }

    @PutMapping(value = "/trash/{businessEntityId}")
    public BusinessEntityDTOResponse restoreBusinessEntity(@PathVariable Integer businessEntityId) {
        return businessEntityService.restore(businessEntityId);
    }

    @DeleteMapping(value = "/{businessEntityId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer businessEntityId) {
        businessEntityService.delete(businessEntityId);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<String> handleParseException(ParseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
