package mx.cdmadero.tecnm.web_project.shared.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import mx.cdmadero.tecnm.web_project.shared.dtos.interfaces.ResponseDTO;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class StorageTransactionsDTOResponse implements ResponseDTO {
    public StorageDTOResponse storage;
    public List<TransactionDTOResponse> transactions;

    @Override
    public Integer getId() {
        return storage.getId();
    }
}
