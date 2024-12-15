package mx.cdmadero.tecnm.web_project.presentation.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.BusinessEntityDTOResponse;
import mx.cdmadero.tecnm.web_project.shared.dtos.responses.StorageDTOResponse;
import mx.cdmadero.tecnm.web_project.shared.dtos.interfaces.OriginDestiny;
import mx.cdmadero.tecnm.web_project.shared.enums.OriginDestinyType;

import java.io.IOException;

public class OriginDestinyDeserializer extends JsonDeserializer<OriginDestiny> {
    @Override
    public OriginDestiny deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        var node = parser.getCodec().readTree(parser);
        String type = node.get("originDestinyType").toString().replaceAll("\"", "");
        ObjectMapper mapper = (ObjectMapper) parser.getCodec();
        if (OriginDestinyType.STORAGE.name().equals(type)) {
            return mapper.treeToValue(node, StorageDTOResponse.class);
        } else if (OriginDestinyType.CLIENT.name().equals(type) || OriginDestinyType.PROVIDER.name().equals(type)) {
            return mapper.treeToValue(node, BusinessEntityDTOResponse.class);
        } else {
            throw new IllegalArgumentException("Tipo desconocido: " + type);
        }
    }
}
