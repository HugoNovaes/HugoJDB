package database.classes;

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
}
