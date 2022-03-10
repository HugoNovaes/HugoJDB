package database.tests;

import database.classes.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DatabaseTest {
    public static void main(String[] args) {
        Database db = new Database();

        Repository repository = db.createRepository("Balaio de gato",
                new Column("Id", FieldType.INTEGER, true),
                new Column("Nome", FieldType.STRING, 50),
                new Column("Raça", FieldType.STRING, 80),
                new Column("Nascimento", FieldType.DATE),
                new Column("Peso", FieldType.NUMERIC)
        );

        repository.addValues(3, "Rapadura", "Viralata", new Date(2008, Calendar.NOVEMBER, 14), 3.9D);
        repository.addValues(5, "Adamastor", "Angorá", new Date(2018, Calendar.MAY, 30), 4.1D);
        repository.addValues(1, "Tom", "Viralata", new Date(2020, Calendar.SEPTEMBER, 25), 3.8D);
        repository.addValues(7, "Drako", "Siamês", new Date(2019, Calendar.AUGUST, 21), 3D);
        repository.addValues(11, "Cato", "Angorá", new Date(2007, Calendar.JANUARY, 19), 3.7D);
        repository.addValues(2, "Cleyton", "Siamês", new Date(2016, Calendar.APRIL, 5), 4.9D);
        repository.addValues(9, "Toinzim", "Viralata", new Date(2017, Calendar.AUGUST, 3), 5D);
        repository.addValues(4, "Ximbica", "Viralata", new Date(2014, Calendar.JULY, 29), 4.1D);
        repository.addValues(12, "Zeca", "Angorá", new Date(2022, Calendar.JANUARY, 4), 3.9D);
        repository.addValues(8, "Matraca", "Viralata", new Date(2007, Calendar.AUGUST, 9), 3.8D);
        repository.addValues(6, "Mel", "Siamês", new Date(2008, Calendar.DECEMBER, 30), 4.75D);
        repository.addValues(10, "Xampu", "Siamês", new Date(2004, Calendar.AUGUST, 14), 4.01D);

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
