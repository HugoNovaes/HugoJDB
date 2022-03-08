package database.classes;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.*;

public class Record implements Comparable<Record>, Comparator {
    //private final Map<String, Field> fields = new HashMap<>();
    private final List<Field> fields = new LinkedList<>();
    private final int index;
    private Field keyField;

    public Record(int index) {
        this.index = index;
    }

    public Field getField(String name) {
        return this.fields.stream().filter(d -> d.getName().equals(name)).findFirst().orElse(null);
    }

    public Field getField(int i) {
        return this.fields.get(i);
    }

    public Field addField(Field field) {
        Field existing = this.getField(field.getName());
        if (existing != null) {
            throw new KeyAlreadyExistsException("Field " + field.getName() + " already exists.");
        }
        return this.fields.add(field) ? field : null;
    }

    public int getFieldsCount() {
        return this.fields.size();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Record{");
        for (Field field : this.fields) {
            sb.append(field.getName()).append(" : ").append(field.getValue()).append("; ");
        }
        sb.append("}");

        return sb.toString();
    }

    private Field getKeyField() {
        if (this.keyField != null) {
            return this.keyField;
        }

        Optional<Field> optional = this.fields.stream().filter(Column::isPrimaryKey).findFirst();
        if (!optional.isPresent()) {
            return null;
        }
        this.keyField = optional.get();
        return this.keyField;
    }

    @Override
    public int compareTo(Record o) {
        Field keyField = this.getKeyField();
        if (keyField == null) {
            return 0;
        }
        FieldValue thisValue = keyField.getValue();
        FieldValue otherValue = o.getKeyField().getValue();
        return thisValue == null ? 0 : thisValue.compareTo(otherValue);
    }

    @Override
    public int compare(Object o1, Object o2) {
        return ((Record) o1).compareTo((Record) o2);
    }
}
