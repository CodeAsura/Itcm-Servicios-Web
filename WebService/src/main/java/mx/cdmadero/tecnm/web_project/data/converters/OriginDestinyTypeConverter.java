package mx.cdmadero.tecnm.web_project.data.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mx.cdmadero.tecnm.web_project.shared.enums.OriginDestinyType;

@Converter(autoApply = true)
public class OriginDestinyTypeConverter implements AttributeConverter<OriginDestinyType, String> {
    @Override
    public String convertToDatabaseColumn(OriginDestinyType originDestinyType) {
        return originDestinyType.getTableName();
    }

    @Override
    public OriginDestinyType convertToEntityAttribute(String s) {
        return s != null ?  OriginDestinyType.fromTableName(s) : null;
    }
}
