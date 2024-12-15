package mx.cdmadero.tecnm.web_project.data.repositories;

import mx.cdmadero.tecnm.web_project.data.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer>, JpaSpecificationExecutor<Location> {
    @Query(
            value = "SELECT * FROM location l " +
            "WHERE l.storage_id = :storageId",
            nativeQuery = true
    )
    List<Location> findAllByStorage(@Param("storageId") Integer storageId);

    @Query(
            value = "SELECT * FROM location l " +
            "WHERE l.product_id = :productId " +
            "AND l.storage_id = :storageId",
            nativeQuery = true
    )
    Location findByStorageAndProduct(
            @Param("storageId") Integer storageId,
            @Param("productId") Integer productId
    );
}
