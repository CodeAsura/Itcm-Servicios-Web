package mx.cdmadero.tecnm.web_project.data.repositories;

import mx.cdmadero.tecnm.web_project.data.entities.BusinessEntity;
import mx.cdmadero.tecnm.web_project.shared.enums.BusinessEntityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusinessEntityRepository extends JpaRepository<BusinessEntity, Integer>, JpaSpecificationExecutor<BusinessEntity> {
    List<BusinessEntity> findByTypeAndIsActive(BusinessEntityType type, boolean isActive);
}
