package database.tests;

import database.classes.*;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

public class PerformanceTest {

    public static void main(String[] args) {
        Database db = new Database("Zoo");
        Repository repository = db.createRepository("Animals",
                new Column("Id", FieldType.INTEGER),
                new Column("Name", FieldType.STRING, 50),
                new Column("Gender", FieldType.STRING, 1),
                new Column("BirthDate", FieldType.DATE),
                new Column("Weight", FieldType.NUMERIC),
                new Column("Species", FieldType.STRING, 40)
        );

        String[] names = {"Sulivan", "Mariot", "Taily", "Buddy", "Terence", "Paully", "Jeky", "Tory"};
        String[] genders = {"Male", "Female"};
        String[] species = {"Bird", "Canine", "Cat", "Fish", "Bovine", "Goat", "Antelope", "Marsupial"};

        Random rnd = new Random();
        int count = 1_000_000;
        System.out.println("Performance test - adding " + count + " records to repository...");

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {

            String name = names[rnd.nextInt(names.length)];
            String gender = genders[rnd.nextInt(genders.length)];
            String specie = species[rnd.nextInt(species.length)];

            Date birthDate = new Date(rnd.nextInt(25) + 1980, rnd.nextInt(12) + 1, rnd.nextInt(28) + 1);
            repository.addValues(i + 1, name, gender, birthDate, (1 + rnd.nextDouble()) * 10, specie);
        }

        long elapsed = System.currentTimeMillis() - startTime;

        Object[] records = repository.getRecords().toArray();
        for (int i = 0; i < 50; i++) {
            Record record = (Record) records[rnd.nextInt(count)];
            System.out.println(record);
        }

        System.out.println("Total time to add " + count + " records: " + elapsed + "ms");
    }

}
