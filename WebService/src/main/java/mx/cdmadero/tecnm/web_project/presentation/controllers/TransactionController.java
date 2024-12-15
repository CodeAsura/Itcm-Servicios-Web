package mx.cdmadero.tecnm.web_project.presentation.controllers;

import mx.cdmadero.tecnm.web_project.domain.services.TransactionService;
import mx.cdmadero.tecnm.web_project.shared.dtos.TransactionDTO;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.StorageTransactionsDTOResponse;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.TransactionDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<StorageTransactionsDTOResponse> getAllTransactions(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        return transactionService.getTransactionsFromAllStorages(startDate, endDate);
    }

    @GetMapping(value = "/client/{clientId}")
    public List<TransactionDTOResponse> getTransactionsByClient(
            @PathVariable Integer clientId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        return transactionService.getTransactionsByClient(clientId, startDate, endDate);
    }

    @GetMapping(value = "/product/{productId}")
    public List<TransactionDTOResponse> getTransactionsByProduct(
            @PathVariable Integer productId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        return transactionService.getTransactionsByProduct(productId, startDate, endDate);
    }


    @GetMapping(value = "/employee/{employeeId}")
    public List<TransactionDTOResponse> getTransactionsByEmployee(
            @PathVariable Integer employeeId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        return transactionService.getTransactionsByEmployee(employeeId, startDate, endDate);
    }

    @GetMapping(value = "/storage/{storageId}")
    public List<TransactionDTOResponse> getTransactionsByStorage(
            @PathVariable Integer storageId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        return transactionService.getTransactionsByStorage(storageId, startDate, endDate);
    }

    @GetMapping(value = "/originDestinyStorage/{originDestinyId}")
    public List<TransactionDTOResponse> getTransactionsByOriginDestinyStorage(
            @PathVariable Integer originDestinyId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        return transactionService
                .getTransactionsByOriginDestinyStorage(originDestinyId, startDate, endDate);
    }

    @GetMapping(value = "/provider/{providerId}")
    public List<TransactionDTOResponse> getTransactionsByProvider(
            @PathVariable Integer providerId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        return transactionService.getTransactionsByProvider(providerId, startDate, endDate);
    }

    @GetMapping(value = "/storage/{storageId}/product/{productId}")
    public List<TransactionDTOResponse> getTransactionsByProductAndStorage(
            @PathVariable("storageId") Integer storageId,
            @PathVariable("productId") Integer productId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        return transactionService.getTransactionsByProductAndStorage(productId, storageId, startDate, endDate);
    }

    @GetMapping(value = "/trash")
    public List<TransactionDTOResponse> getDeletedTransactions() {
        return transactionService.getDeleted();
    }

    @PostMapping
    public TransactionDTOResponse registerTransaction(
            @RequestBody TransactionDTO registerTransactionRequest
    ) {
        return transactionService.registerTransaction(registerTransactionRequest);
    }

    @PutMapping(value = "/trash/{transactionId}")
    public TransactionDTOResponse restoreTransaction(@PathVariable Integer transactionId) {
        return transactionService.restore(transactionId);
    }

    @DeleteMapping
    public void deleteTransaction(@PathVariable Integer transactionId) {
        transactionService.delete(transactionId);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
