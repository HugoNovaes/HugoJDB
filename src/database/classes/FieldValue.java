package database.classes;

public class FieldValue implements Comparable<FieldValue> {
    private boolean isNull;
    private Object value;

    @Override
    public String toString() {
        return value == null ? "null" : "" + value;
    }

    public FieldValue(Object value) {
        this.setValue(value);
    }

    public void setValue(Object value) {
        this.value = value;
        this.isNull = value == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        FieldValue other = new FieldValue(o);
        FieldValue that = (FieldValue) other;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public void clear() {
        this.value = new Object();
        this.isNull = true;
    }

    public Object getValue() {
        return this.value;
    }

    @Override
    public int compareTo(FieldValue o) {
        if (this.value instanceof Integer) {
            Integer value = (Integer) this.value;
            return value.compareTo((Integer) o.value);
        }

        if (this.value instanceof String) {
            String value = (String) this.value;
            return value.compareTo((String) o.value);
        }

        if (this.value instanceof Double) {
            Double value = (Double) this.value;
            return value.compareTo((Double) o.value);
        }

        if (this.value instanceof Boolean) {
            Boolean value = (Boolean) this.value;
            return value.compareTo((Boolean) o.value);
        }

        return 0;
    }
}
