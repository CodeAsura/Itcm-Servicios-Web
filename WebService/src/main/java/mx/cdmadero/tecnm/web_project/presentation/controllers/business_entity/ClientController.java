package mx.cdmadero.tecnm.web_project.presentation.controllers.business_entity;

import mx.cdmadero.tecnm.web_project.shared.dtos.responses.BusinessEntityDTOResponse;
import mx.cdmadero.tecnm.web_project.shared.enums.BusinessEntityType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/client")
public class ClientController extends BusinessEntityController {
    @GetMapping
    public List<BusinessEntityDTOResponse> getAllClients() {
        return businessEntityService.getAllBusinessEntitiesByType(BusinessEntityType.CLIENT);
    }
    @GetMapping(value = "/trash")
    public List<BusinessEntityDTOResponse> getDeletedClients() {
        return businessEntityService.getDeletedByType(BusinessEntityType.CLIENT);
    }
}
