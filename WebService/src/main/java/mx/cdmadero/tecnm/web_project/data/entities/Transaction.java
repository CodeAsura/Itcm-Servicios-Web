package mx.cdmadero.tecnm.web_project.data.entities;

import jakarta.persistence.*;
import lombok.*;
import mx.cdmadero.tecnm.web_project.shared.enums.TransactionType;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class Transaction implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "storage_id")
    private Storage storage;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToOne
    @JoinColumn(name = "origin_destiny_id")
    private OriginDestiny originDestiny;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private BusinessEntity employee;
    private boolean isActive;
    private Integer quantity;
    private LocalDateTime createdAt;
}