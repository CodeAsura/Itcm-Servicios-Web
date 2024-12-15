package mx.cdmadero.tecnm.web_project.data.repositories;

import mx.cdmadero.tecnm.web_project.data.entities.Transaction;
import mx.cdmadero.tecnm.web_project.shared.enums.OriginDestinyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Integer>, JpaSpecificationExecutor<Transaction> {

    @Query(
            value = "SELECT * FROM transaction t " +
            "WHERE t.created_at BETWEEN :startDate AND :endDate",
            nativeQuery = true
    )
    List<Transaction> findAllByDate(
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

    @Query(
            value = "SELECT " +
            "t.id, " +
            "t.employee_id, " +
            "t.origin_destiny_id, " +
            "t.product_id, " +
            "t.storage_id, " +
            "t.quantity, " +
            "t.created_at, " +
            "t.transaction_type, " +
            "t.is_active " +
            "FROM transaction t " +
            "JOIN origin_destiny od " +
            "ON od.id = t.origin_destiny_id " +
            "WHERE od.reference_id = :referenceId " +
            "AND t.is_active = true " +
            "AND od.type = :odType " +
            "AND t.created_at BETWEEN :startDate AND :endDate",
            nativeQuery = true
    )
    List<Transaction> findByType(
            @Param("odType") String type,
            @Param("referenceId") Integer referenceId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query(
            "SELECT t FROM Transaction t " +
            "JOIN t.product p " +
            "WHERE p.id = :productId " +
            "AND t.isActive = true " +
            "AND t.createdAt BETWEEN :startDate AND :endDate"
    )
    List<Transaction> findByProduct(
            @Param("productId") Integer productId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query(
            "SELECT t FROM Transaction t " +
            "JOIN t.employee e " +
            "WHERE e.id = :employeeId " +
            "AND t.isActive = true " +
            "AND t.createdAt BETWEEN :startDate AND :endDate"
    )
    List<Transaction> findByEmployee(
            @Param("employeeId") Integer employeeId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query(
            "SELECT t FROM Transaction t " +
                    "JOIN t.storage s " +
                    "JOIN t.product p " +
                    "WHERE s.id = :storageId " +
                    "AND t.isActive = true " +
                    "AND t.createdAt BETWEEN :startDate AND :endDate"
    )
    List<Transaction> findByStorage(
            @Param("storageId") Integer storageId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query(
            "SELECT t FROM Transaction t " +
            "JOIN t.storage s " +
            "JOIN t.product p " +
            "WHERE s.id = :storageId " +
            "AND p.id = :productId " +
            "AND t.isActive = true " +
            "AND t.createdAt BETWEEN :startDate AND :endDate"
    )
    List<Transaction> findByStorageAndProduct(
            @Param("storageId") Integer storageId,
            @Param("productId") Integer productId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

}



