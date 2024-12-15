package mx.cdmadero.tecnm.web_project.shared.enums;

import lombok.Getter;

@Getter
public enum OriginDestinyType {
    STORAGE("storage"),
    CLIENT("business_entity"),
    PROVIDER("business_entity");
    private final String tableName;

    OriginDestinyType(String tableName) {
        this.tableName =  tableName;
    }

    public static OriginDestinyType fromTableName(String tableName) {
        for(OriginDestinyType type : OriginDestinyType.values()) {
            if(type.getTableName().equals(tableName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid table name: " + tableName);
    }
}
