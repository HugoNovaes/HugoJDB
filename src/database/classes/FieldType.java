package database.classes;

import java.util.Date;

public enum FieldType {
    INTEGER(1, Integer.class),
    NUMERIC(2, Double.class),
    STRING(3, String.class),
    BOOLEAN(4, Boolean.class),
    BINARY(5, Byte[].class),
    DATE(6, Date.class);

    private final int id;
    private final Class baseType;

    @Override
    public String toString() {
        return "FieldType{id: "+this.id + "; baseType: " + this.baseType +"}";
    }

    FieldType(int id, Class baseType) {
        this.id = id;
        this.baseType = baseType;
    }
}
