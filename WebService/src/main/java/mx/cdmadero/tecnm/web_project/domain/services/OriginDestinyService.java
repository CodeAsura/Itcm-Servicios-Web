package mx.cdmadero.tecnm.web_project.domain.services;

import mx.cdmadero.tecnm.web_project.data.entities.OriginDestiny;
import mx.cdmadero.tecnm.web_project.data.repositories.OriginDestinyRepository;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.OriginDestinyDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginDestinyService {
    @Autowired
    private OriginDestinyRepository originDestinyRepository;

    public OriginDestiny createOriginDestiny(OriginDestinyDTOResponse originDestinyDTO) {
        OriginDestiny originDestiny = OriginDestiny
                .builder()
                .referenceId(originDestinyDTO.getId())
                .type(originDestinyDTO.getType())
                .build();
        originDestiny.setActive(true);
        return originDestinyRepository.save(originDestiny);
    }
}
