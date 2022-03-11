package database.classes;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private final Map<String, Repository> repositoryList = new HashMap<>();
    private final String databaseName;

    public Database(String databaseName) {
        this.databaseName = databaseName;
    }

    public Repository createRepository(String name) {
        Repository existing = this.repositoryList.get(name);
        if (existing != null) {
            throw new KeyAlreadyExistsException("Repository " + name + " already exists.");
        }
        Repository result = new Repository(name);
        this.repositoryList.put(name, result);
        return result;
    }

    public Repository createRepository(String name, Column... columns) {
        Repository result = this.createRepository(name);
        result.addColumn(columns);
        return result;
    }

}
