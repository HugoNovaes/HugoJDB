package database.classes;

import database.classes.exceptions.InvalidFormatException;

import java.util.Date;

public class Field extends Column implements Comparable<Field> {
    private Column column;
    private FieldValue value;

    public FieldValue getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = new FieldValue(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Field field = (Field) o;

        return value.equals(field.value);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    public Field(Column column, Object value) {
        super(column.getName(), column.getType(), column.getSize(), column.isNullable(), column.isPrimaryKey());
        this.value = new FieldValue(value);
    }

    @Override
    public int compareTo(Field o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return "Field{" +
                "column=" + column +
                ", value=" + value +
                '}';
    }

    public Integer asInt() throws InvalidFormatException, NumberFormatException {
        if (this.getType() != FieldType.INTEGER) {
            throw new InvalidFormatException("Field value is not of type INTEGER.");
        }
        return Integer.parseInt(this.value.toString());
    }

    public Double asDouble() throws InvalidFormatException, NumberFormatException {
        if (this.getType() != FieldType.NUMERIC) {
            throw new InvalidFormatException("Field value is not of type NUMERIC.");
        }
        return Double.parseDouble(this.value.toString());
    }

    public String asString() {
        return this.value.toString();
    }

    public Boolean asBoolean() throws InvalidFormatException, NumberFormatException {
        if (this.getType() != FieldType.BOOLEAN) {
            throw new InvalidFormatException("Field value is not of type BOOLEAN.");
        }
        return Boolean.parseBoolean(this.value.toString());
    }

    public Byte[] asArray() throws InvalidFormatException {
        if (this.getType() != FieldType.BINARY) {
            throw new InvalidFormatException("Field value is not of type BINARY.");
        }
        return (Byte[]) this.value.getValue();
    }

    public Date asDate() throws InvalidFormatException, IllegalArgumentException {
        if (this.getType() != FieldType.DATE) {
            throw new InvalidFormatException("Field value is not of type DATE.");
        }
        return new Date(Date.parse(this.value.toString()));
    }
}
