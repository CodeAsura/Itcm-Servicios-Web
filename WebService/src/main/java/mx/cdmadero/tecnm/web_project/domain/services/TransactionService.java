package mx.cdmadero.tecnm.web_project.domain.services;

import mx.cdmadero.tecnm.web_project.data.entities.*;
import mx.cdmadero.tecnm.web_project.data.repositories.*;
import mx.cdmadero.tecnm.web_project.domain.CommonOperations;
import mx.cdmadero.tecnm.web_project.shared.dtos.TransactionDTO;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.*;
import mx.cdmadero.tecnm.web_project.shared.enums.OriginDestinyType;
import mx.cdmadero.tecnm.web_project.shared.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService extends CommonOperations<Transaction, TransactionRepository, TransactionDTOResponse> {

    @Autowired
    private StorageProductService storageProductService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private BusinessEntityService businessEntityService;
    @Autowired
    private OriginDestinyService originDestinyService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BusinessEntityRepository businessEntityRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private StorageRepository storageRepository;

    public TransactionDTOResponse registerTransaction(TransactionDTO registerTransactionRequest) {
        BusinessEntity employee = businessEntityRepository
                .findById(registerTransactionRequest.employeeId)
                .orElseThrow(() -> getNotFoundException("Employee", registerTransactionRequest.employeeId));

        Product product = productRepository
                .findById(registerTransactionRequest.productId)
                .orElseThrow(() -> getNotFoundException("Product", registerTransactionRequest.productId));

        OriginDestiny originDestiny = originDestinyService
                .createOriginDestiny(registerTransactionRequest.originDestiny);

        Storage storage = storageRepository
                .findById(registerTransactionRequest.storageId)
                .orElseThrow(() -> getNotFoundException("Storage", registerTransactionRequest.storageId));

        Transaction transaction = Transaction
                .builder()
                .employee(employee)
                .product(product)
                .storage(storage)
                .quantity(registerTransactionRequest.quantity)
                .originDestiny(originDestiny)
                .transactionType(registerTransactionRequest.type)
                .createdAt(LocalDateTime.now())
                .build();
        transaction.setActive(true);
        var response = transactionRepository.save(transaction);
        var productId = response.getProduct().getId();
        var storageId = response.getStorage().getId();
        // Update stock amount in StorageProduct
        if(response.getTransactionType() == TransactionType.IN)  {
            storageProductService.updateStockValue(productId, storageId, response.getQuantity());
        } else if (response.getTransactionType() == TransactionType.OUT) {
            var amount = response.getQuantity() * -1;
            storageProductService.updateStockValue(productId, storageId, amount);
        }
        return toDTO(response);
    }

    public List<TransactionDTOResponse> getTransactionsByProduct(Integer productId, String startDate, String endDate) {
        LocalDateTime startDateTime = toLocalDateTime(startDate, false);
        LocalDateTime endDateTime = toLocalDateTime(endDate, true);
        return transactionRepository
                .findByProduct(productId, startDateTime, endDateTime)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<TransactionDTOResponse> getTransactionsByEmployee(Integer employeeId, String startDate, String endDate) {
        LocalDateTime startDateTime = toLocalDateTime(startDate, false);
        LocalDateTime endDateTime = toLocalDateTime(endDate, true);
        return transactionRepository
                .findByEmployee(employeeId, startDateTime, endDateTime)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<StorageTransactionsDTOResponse> getTransactionsFromAllStorages(String startDate, String endDate) {
        var storageTransactionMap = transactionRepository
                .findAllByDate(startDate, endDate.concat(" 23:59:59.999999"))
                .stream()
                .collect(Collectors.groupingBy(Transaction::getStorage));
        return toStorageTransactionDTO(storageTransactionMap);
    }

    public List<TransactionDTOResponse> getTransactionsByStorage(Integer storageId, String startDate, String endDate) {
        LocalDateTime startDateTime = toLocalDateTime(startDate, false);
        LocalDateTime endDateTime = toLocalDateTime(endDate, true);
        return  transactionRepository
                .findByStorage(storageId, startDateTime, endDateTime)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<TransactionDTOResponse> getTransactionsByOriginDestinyStorage(Integer storageId, String startDate, String endDate) {
        LocalDateTime startDateTime = toLocalDateTime(startDate, false);
        LocalDateTime endDateTime = toLocalDateTime(endDate, true);
        return transactionRepository
                .findByType(OriginDestinyType.STORAGE.getTableName(), storageId, startDateTime, endDateTime)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<TransactionDTOResponse> getTransactionsByClient(Integer clientId, String startDate, String endDate) {
        LocalDateTime startDateTime = toLocalDateTime(startDate, false);
        LocalDateTime endDateTime = toLocalDateTime(endDate, true);
        return transactionRepository
                .findByType(OriginDestinyType.CLIENT.getTableName(), clientId, startDateTime, endDateTime)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<TransactionDTOResponse> getTransactionsByProvider(Integer providerId, String startDate, String endDate) {
        LocalDateTime startDateTime = toLocalDateTime(startDate, false);
        LocalDateTime endDateTime = toLocalDateTime(endDate, true);
        List<Transaction> transactions = transactionRepository.findByType(OriginDestinyType.PROVIDER.getTableName(), providerId, startDateTime, endDateTime);
        return transactions.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<TransactionDTOResponse> getTransactionsByProductAndStorage(Integer storageId, Integer productId, String startDate, String endDate) {
        LocalDateTime startDateTime = toLocalDateTime(startDate, false);
        LocalDateTime endDateTime = toLocalDateTime(endDate, true);
        return transactionRepository
                .findByStorageAndProduct(storageId, productId, startDateTime, endDateTime)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public TransactionDTOResponse toDTO(Transaction transaction) {
        OriginDestinyType type = transaction
                .getOriginDestiny()
                .getType();
        Integer originDestinyReferenceId = transaction.getOriginDestiny().getReferenceId();
        Storage storage = transaction.getStorage();
        Product product = transaction.getProduct();
        BusinessEntity employee = transaction.getEmployee();

        TransactionDTOResponse.TransactionDTOResponseBuilder builder = TransactionDTOResponse
                .builder()
                .id(transaction.getId())
                .storage(new StorageDTOResponse(storage))
                .product(new ProductDTOResponse(product))
                .employee(new BusinessEntityDTOResponse(employee))
                .type(transaction.getTransactionType())
                .createdAt(transaction.getCreatedAt())
                .quantity(transaction.getQuantity());

        if (type == OriginDestinyType.CLIENT || type == OriginDestinyType.PROVIDER) {
            BusinessEntityDTOResponse businessEntityDTO = businessEntityService.getById(originDestinyReferenceId);
            builder.originDestiny(businessEntityDTO);
        } else {
            StorageDTOResponse storageDTO = storageService.getById(originDestinyReferenceId);
            builder.originDestiny(storageDTO);
        }

        return builder.build();
    }

    @Override
    public Transaction toEntity(TransactionDTOResponse dto) {
        return null;
    }

    private List<StorageTransactionsDTOResponse> toStorageTransactionDTO(Map<Storage, List<Transaction>> map) {
        return map
                .entrySet()
                .stream()
                .map(entry -> {
                    var storageDTO = storageService.toDTO(entry.getKey());
                    var transactionDTOs = entry
                            .getValue()
                            .stream()
                            .map(this::toDTO)
                            .toList();
                    return StorageTransactionsDTOResponse
                            .builder()
                            .storage(storageDTO)
                            .transactions(transactionDTOs)
                            .build();
                })
                .toList();
    }

    private LocalDateTime toLocalDateTime(String date, boolean endDate) {
        var dateArr = Arrays
                .stream(date.split("-"))
                .mapToInt(Integer::parseInt)
                .toArray();
        if(endDate) {
            return LocalDateTime.of(dateArr[0], dateArr[1], dateArr[2], 23, 59, 59, 999999999);
        } else {
            return LocalDateTime.of(dateArr[0], dateArr[1], dateArr[2], 0, 0, 0, 0);
        }
    }

    private IllegalArgumentException getNotFoundException(String entityName, Integer id) {
        return new IllegalArgumentException(entityName + " not found with ID: " + id);
    }
}
