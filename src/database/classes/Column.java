package database.classes;

public class Column {
    private boolean unique;
    private boolean primaryKey;
    private String name;
    private FieldType type;
    private int size;
    private boolean nullable;

    public boolean isPrimaryKey() {
        return this.primaryKey;
    }

    public boolean isUnique() {
        return this.unique;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", size=" + size +
                ", nullable=" + nullable +
                '}';
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Column c = (Column) o;

        return name.equals(c.name);
    }

    public Column() {
        this.name = "(nothing)";
        this.type = FieldType.STRING;
        this.size = 10;
    }

    public Column(String name) {
        this.name = name;
    }

    public Column(String name, boolean primaryKey) {
        this(name);
        this.primaryKey = primaryKey;
    }

    public Column(String name, FieldType type) {
        this(name);
        this.type = type;
    }

    public Column(String name, FieldType type, boolean primaryKey) {
        this(name, type);
        this.primaryKey = primaryKey;
    }

    public Column(String name, FieldType type, int size) {
        this(name, type);
        this.size = size;
    }

    public Column(String name, FieldType type, int size, boolean nullable) {
        this(name, type, size);
        this.nullable = nullable;
    }

    public Column(String name, FieldType type, int size, boolean nullable, boolean primaryKey) {
        this(name, type, size, nullable);
        this.primaryKey = primaryKey;
    }

    public Column(String name, FieldType type, int size, boolean nullable, Object value) {
        this(name, type, size, nullable);
    }
}
