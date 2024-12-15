package mx.cdmadero.tecnm.web_project.data.seeders;

import mx.cdmadero.tecnm.web_project.data.entities.BusinessEntity;
import mx.cdmadero.tecnm.web_project.data.repositories.BusinessEntityRepository;
import mx.cdmadero.tecnm.web_project.shared.enums.BusinessEntityType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

@Component
@Profile("test")
public class BusinessEntitySeeder implements CommandLineRunner {
    private final BusinessEntityRepository businessEntityRepository;

    public BusinessEntitySeeder(BusinessEntityRepository businessEntityRepository) {
        this.businessEntityRepository = businessEntityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        addEmployees();
        addProviders();
        addClients();
    }

    public void addEmployees() {
        var employees = Arrays.asList(
                BusinessEntity.builder()
                        .name("John Doe")
                        .RFC("EMP001")
                        .email("johndoe@example.com")
                        .phone("555-1234")
                        .created_on(Date.valueOf(LocalDate.now()))
                        .type(BusinessEntityType.EMPLOYEE)
                        .isActive(true)
                        .build(),
                BusinessEntity.builder()
                        .name("Jane Smith")
                        .RFC("EMP002")
                        .email("janesmith@example.com")
                        .phone("555-5678")
                        .created_on(Date.valueOf(LocalDate.now()))
                        .type(BusinessEntityType.EMPLOYEE)
                        .isActive(true)
                        .build()
        );
        businessEntityRepository.saveAll(employees);
    }

    public void addProviders() {
        var providers = Arrays.asList(
                BusinessEntity.builder()
                        .name("Tech Supplies Inc.")
                        .RFC("PROV001")
                        .email("support@techsupplies.com")
                        .phone("555-7890")
                        .created_on(Date.valueOf(LocalDate.now()))
                        .type(BusinessEntityType.PROVIDER)
                        .isActive(true)
                        .build(),
                BusinessEntity.builder()
                        .name("Office Essentials")
                        .RFC("PROV002")
                        .email("sales@officeessentials.com")
                        .phone("555-4567")
                        .created_on(Date.valueOf(LocalDate.now()))
                        .type(BusinessEntityType.PROVIDER)
                        .isActive(true)
                        .build()
        );
        businessEntityRepository.saveAll(providers);
    }

    public void addClients() {
        var clients = Arrays.asList(
                BusinessEntity.builder()
                        .name("Acme Corp")
                        .RFC("CLI001")
                        .email("info@acme.com")
                        .phone("555-0001")
                        .created_on(Date.valueOf(LocalDate.now()))
                        .type(BusinessEntityType.CLIENT)
                        .isActive(true)
                        .build(),
                BusinessEntity.builder()
                        .name("Global Enterprises")
                        .RFC("CLI002")
                        .email("contact@globalenterprises.com")
                        .phone("555-0002")
                        .created_on(Date.valueOf(LocalDate.now()))
                        .type(BusinessEntityType.CLIENT)
                        .isActive(true)
                        .build()
        );
        businessEntityRepository.saveAll(clients);
    }
}
