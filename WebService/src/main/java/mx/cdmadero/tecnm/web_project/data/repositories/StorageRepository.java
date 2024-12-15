package mx.cdmadero.tecnm.web_project.data.repositories;

import mx.cdmadero.tecnm.web_project.data.entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StorageRepository extends JpaRepository<Storage, Integer>, JpaSpecificationExecutor<Storage> {
}