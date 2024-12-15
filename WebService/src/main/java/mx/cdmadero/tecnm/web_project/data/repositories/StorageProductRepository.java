package mx.cdmadero.tecnm.web_project.data.repositories;

import mx.cdmadero.tecnm.web_project.data.entities.StorageProduct;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.stock_dto.StockDTOResponse;
import mx.cdmadero.tecnm.web_project.shared.dtos.StorageProductStockDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StorageProductRepository extends JpaRepository<StorageProduct, Integer>, JpaSpecificationExecutor<StorageProduct> {

    @Query("SELECT new mx.cdmadero.tecnm.web_project.shared.dtos.StorageProductStockDTO(" +
            "new mx.cdmadero.tecnm.web_project.shared.dtos.responses.StorageDTOResponse(s.id, s.name, s.address, s.latitude, s.longitude), " +
            "new mx.cdmadero.tecnm.web_project.shared.dtos.responses.ProductDTOResponse(p.id, p.UPC, p.name, p.description, p.lastCost, p.lastPrice), " +
            "sp.stock) " +
            "FROM StorageProduct sp " +
            "JOIN sp.product p " +
            "JOIN sp.storage s")
    List<StorageProductStockDTO> getExistences();

    @Query("SELECT new mx.cdmadero.tecnm.web_project.shared.dtos.responses.stock_dto.StockDTOResponse(" +
            "new mx.cdmadero.tecnm.web_project.shared.dtos.responses.ProductDTOResponse(p.id, p.UPC, p.name, p.description, p.lastCost, p.lastPrice), " +
            "sp.stock) " +
            "FROM StorageProduct sp " +
            "JOIN sp.product p " +
            "JOIN sp.storage s " +
            "WHERE s.id = :storageId")
    List<StockDTOResponse> getExistencesByStorage(@Param("storageId") Integer storageId);

    @Query("SELECT new mx.cdmadero.tecnm.web_project.shared.dtos.responses.stock_dto.StockDTOResponse(" +
            "new mx.cdmadero.tecnm.web_project.shared.dtos.responses.ProductDTOResponse(p.id, p.UPC, p.name, p.description, p.lastCost, p.lastPrice), " +
            "sp.stock) " +
            "FROM StorageProduct sp " +
            "JOIN sp.product p " +
            "JOIN sp.storage s " +
            "WHERE s.id = :storageId " +
            "AND p.id = :productId ")
    StockDTOResponse getExistencesByStorageAndProduct(@Param("storageId") Integer storageId, @Param("productId") Integer productId);

}


