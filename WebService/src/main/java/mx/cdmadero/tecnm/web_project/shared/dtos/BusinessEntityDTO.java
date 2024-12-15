package mx.cdmadero.tecnm.web_project.shared.dtos;

import mx.cdmadero.tecnm.web_project.shared.enums.BusinessEntityType;

import java.sql.Date;

public class BusinessEntityDTO {
    public String name;
    public String rfc;
    public String email;
    public String phone;
    public Date created_on;
    public BusinessEntityType type;
}
