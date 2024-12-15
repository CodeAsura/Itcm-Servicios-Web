package mx.cdmadero.tecnm.web_project.domain.services;

import mx.cdmadero.tecnm.web_project.data.entities.BusinessEntity;
import mx.cdmadero.tecnm.web_project.data.repositories.BusinessEntityRepository;
import mx.cdmadero.tecnm.web_project.domain.CommonOperations;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.BusinessEntityDTOResponse;
import mx.cdmadero.tecnm.web_project.shared.enums.BusinessEntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessEntityService extends CommonOperations<BusinessEntity, BusinessEntityRepository, BusinessEntityDTOResponse> {
    @Autowired
    private BusinessEntityRepository businessEntityRepository;

    public List<BusinessEntityDTOResponse> getAllBusinessEntitiesByType(BusinessEntityType type) {
        return businessEntityRepository
                .findByTypeAndIsActive(type, true)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<BusinessEntityDTOResponse> getDeletedByType(BusinessEntityType type) {
        return getDeleted()
                .stream()
                .filter(o -> o.getType() == type)
                .toList();

    }

    @Override
    public BusinessEntityDTOResponse toDTO(BusinessEntity entity) {
        return BusinessEntityDTOResponse
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .rfc(entity.getRFC())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .created_on(entity.getCreated_on())
                .type(entity.getType())
                .build();
    }

    @Override
    public BusinessEntity toEntity(BusinessEntityDTOResponse dto) {
        return BusinessEntity
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .RFC(dto.getRfc())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .created_on(dto.getCreated_on())
                .type(dto.getType())
                .build();
    }
}
