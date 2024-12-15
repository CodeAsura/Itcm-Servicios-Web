package mx.cdmadero.tecnm.web_project.data.repositories;

import mx.cdmadero.tecnm.web_project.data.entities.OriginDestiny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OriginDestinyRepository extends JpaRepository<OriginDestiny, Integer>, JpaSpecificationExecutor<OriginDestiny> {
}
