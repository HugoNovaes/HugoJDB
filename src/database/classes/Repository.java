package database.classes;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.*;

public class Repository {
    private final Map<Integer, Record> records = new HashMap<>();
    private final Map<String, Column> columns = new HashMap<>();
    private final List<String> columnNames = new ArrayList<>();
    private final String name;

    public Repository(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Collection<Record> getRecords() {
        return this.records.values();
    }

    public void addColumn(Column... args) {
        for (Column c : args) {
            Column existing = this.columns.get(c.getName());
            if (existing != null) {
                throw new KeyAlreadyExistsException("Column " + c.getName() + " already exists!");
            }

            this.columns.put(c.getName(), c);
            this.columnNames.add(c.getName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    private Object getWrappedValue(Object value) {
        if (Integer.class.isInstance(value)) {
            Integer result = (Integer) value;
            return result;
        }

        if (Double.class.isInstance(value)) {
            Double result = (Double) value;
            return result;
        }

        if (Boolean.class.isInstance(value)) {
            Boolean result = (Boolean) value;
            return result;
        }

        if (String.class.isInstance(value)) {
            String result = (String) value;
            return result;
        }

        return value;
    }

    /**
     *  Add a new record to the repository with the field values specified in the <strong>args</strong> parameter.
     *
     * @param args The parameter <strong>args</strong> contains the list with the exact length of the columns' length
     *             and must contain the values to set each field into the record.
     */
    public Record addValues(Object... args) {
        if (args == null) {
            throw new NullPointerException("Cannot add null values");
        }

        if (args.length != this.columns.size()) {
            throw new ArrayStoreException("The size of arguments is not equal to the number of columns.");
        }

        Integer index = this.records.size();
        Record record = new Record(index);

        for (int i = 0; i < args.length; i++) {
            String colName = this.columnNames.get(i);
            Column column = this.columns.get(colName);

            Object value = getWrappedValue(args[i]);

            Field field = new Field(column, value);

            if (field.isPrimaryKey() || field.isUnique()) {
                Record r = this.find(args[i]);
                if (r != null) {
                    throw new KeyAlreadyExistsException("Violation of primary key with value " + value + " to the column " + colName);
                }
            }

            record.addField(field);
        }
        this.records.put(index, record);
        return record;
    }

    private Column getKeyColumn() {
        Optional<Column> optional = this.columns.values().stream().filter(Column::isPrimaryKey).findFirst();
        return optional.orElse(null);
    }

    public Record find(Object value) {
        Column column = getKeyColumn();
        if (column == null) {
            System.out.println("No field key was defined.");
            return null;
        }

        for (Record record : this.records.values()) {
            Field field = record.getField(column.getName());
            FieldValue fieldValue = field.getValue();

            if (fieldValue != null && fieldValue.equals(value)) {
                return record;
            }
        }
        return null;
    }

    public List<Record> find(String columnName, Object value) {
        Column column = this.columns.get(columnName);
        if (column == null) {
            return null;
        }

        List<Record> result = new ArrayList<>();

        for (Record record : this.records.values()) {
            Field field = record.getField(columnName);
            FieldValue fieldValue = field.getValue();

            if (fieldValue != null && fieldValue.equals(value)) {
                result.add(record);
            }
        }
        return result;
    }

    /**
     * Select all records from the repository ordered by its primary key.
     * The primary key used to be the first field in the collection, but it is
     * up to you define any other field, since its value is not duplicated in
     * any other record into the repository.
     *
     * @return A list of Record's (List<Record>) ordered by the field key's value.
     */
    public List<Record> selectAll() {
        Collection<Record> records = this.records.values();
        List<Record> result = new ArrayList<>(records);
        result.sort(Record::compareTo);
        return result;
    }

    public List<Record> selectAll(String columnName) {
        Collection<Record> records = this.records.values();
        List<Record> result = new ArrayList<>(records);
        result.sort((o1, o2) -> o1.getField(columnName).compareTo(o2.getField(columnName)));
        return result;
    }
}
