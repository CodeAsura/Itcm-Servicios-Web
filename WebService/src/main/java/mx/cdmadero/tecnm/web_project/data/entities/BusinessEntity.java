package mx.cdmadero.tecnm.web_project.data.entities;

import jakarta.persistence.*;
import lombok.*;
import mx.cdmadero.tecnm.web_project.shared.enums.BusinessEntityType;

import java.sql.Date;
import java.util.List;

/**
 * Represent either an employee, a client or a provider
 * */
@Entity(name = "business_entity")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class BusinessEntity implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String RFC;
    private String email;
    private String phone;
    private Date created_on;
    @OneToMany(mappedBy = "employee")
    private List<Transaction> transactions;
    @Enumerated(EnumType.ORDINAL)
    private BusinessEntityType type;
    private boolean isActive;
}
