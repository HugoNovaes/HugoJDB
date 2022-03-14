package database.tests;

import database.classes.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DatabaseTest {
    public static void main(String[] args) {
        Database db = new Database("Pets");

        Repository repository = db.createRepository("Balaio de gato",
                new Column("Id", FieldType.INTEGER, true),
                new Column("Nome", FieldType.STRING, 50),
                new Column("Raça", FieldType.STRING, 80),
                new Column("Nascimento", FieldType.DATE),
                new Column("Peso", FieldType.NUMERIC)
        );

        repository.addValues(3, "Rapadura", "Viralata", LocalDate.of(2008, 11, 14), 3.9D);
        repository.addValues(5, "Adamastor", "Angorá", LocalDate.of(2018, 5, 30), 4.1D);
        repository.addValues(1, "Tom", "Viralata", LocalDate.of(2020, 9, 25), 3.8D);
        repository.addValues(7, "Drako", "Siamês", LocalDate.of(2019, 8, 21), 3D);
        repository.addValues(11, "Cato", "Angorá", LocalDate.of(2007, 1, 19), 3.7D);
        repository.addValues(2, "Cleyton", "Siamês", LocalDate.of(2016, 4, 5), 4.9D);
        repository.addValues(9, "Toinzim", "Viralata", LocalDate.of(2017, 10, 3), 5D);
        repository.addValues(4, "Ximbica", "Viralata", LocalDate.of(2014, 7, 29), 4.1D);
        repository.addValues(12, "Zeca", "Angorá", LocalDate.of(2022, 1, 4), 3.9D);
        repository.addValues(8, "Matraca", "Viralata", LocalDate.of(2007, 8, 9), 3.8D);
        repository.addValues(6, "Mel", "Siamês", LocalDate.of(2008, 12, 30), 4.75D);
        repository.addValues(10, "Xampu", "Siamês", LocalDate.of(2004, 8, 14), 4.01D);

        System.out.println("Repository has " + repository.getRecords().size() + " records.");

        System.out.println("----------Unsorted list----------");
        for (Record record : repository.getRecords()) {
            System.out.println(record);
        }

        System.out.println("----------Sorted by Id----------");
        List<Record> records = repository.selectAll();

        for (Object record : records) {
            System.out.println(record);
        }

        selectAllOrderBy(repository, "Raça");
        selectAllOrderBy(repository, "Nome");

        System.out.println("\nFind by Id = 8:");
        Record record = repository.find(8);
        System.out.println(record);

        System.out.println("\nFind by Name = 'Tom':");
        List<Record> foundList = repository.find("Nome", "Tom");
        for (Record found : foundList) {
            System.out.println(found);
        }

        System.out.println("\nFind by Raça = 'Angorá':");
        foundList = repository.find("Raça", "Angorá");
        for (Record found : foundList) {
            System.out.println(found);
        }
    }

    private static void selectAllOrderBy(Repository repository, String columnName) {
        System.out.println("----------Sorted by " + columnName + "----------");
        List<Record> records = repository.selectAll("Nome");
        for (Object record : records) {
            System.out.println(record);
        }
    }
}
