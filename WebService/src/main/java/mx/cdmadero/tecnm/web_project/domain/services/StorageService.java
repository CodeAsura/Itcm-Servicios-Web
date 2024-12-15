package mx.cdmadero.tecnm.web_project.domain.services;

import mx.cdmadero.tecnm.web_project.data.entities.Storage;
import mx.cdmadero.tecnm.web_project.data.repositories.StorageRepository;
import mx.cdmadero.tecnm.web_project.domain.CommonOperations;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.StorageDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService extends CommonOperations<Storage, StorageRepository, StorageDTOResponse> {
    @Autowired
    private StorageRepository storageRepository;

    @Override
    public StorageDTOResponse toDTO(Storage storage) {
        return new StorageDTOResponse(storage);
    }

    @Override
    public Storage toEntity(StorageDTOResponse storageDTO) {
        return Storage.builder()
                .id(storageDTO.getId())
                .name(storageDTO.getName())
                .address(storageDTO.getAddress())
                .latitude(storageDTO.getLatitude())
                .longitude(storageDTO.getLongitude())
                .build();
    }
}
