package tasks;

import tasks.one.BracketCombinations;
import tasks.three.SumOfDigits;
import tasks.two.RoadPrice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static tasks.two.RoadPriceFiles.*;

public class ProgramRun {

    /**
     * Controller.
     */
    public static void run() {
        preview();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String event;
        try {
            while ((event = reader.readLine()) != null) {
                switch (event) {
                    case "1" -> {
                        System.out.println("Task 1. Enter a number:");
                        new BracketCombinations().run(reader);
                        preview();
                    }
                    case "2" -> {
                        createTaskDataIfDoesNotExist();
                        new RoadPrice().run();
                        String dir = "Directory: " + System.getProperty("user.dir") + "/" + FILE_DIR;
                        if (System.getProperty("os.name").toLowerCase().contains("win")) {
                            dir = dir.replace('/', '\\');
                        }
                        System.out.println("Task 2.\n" + dir);
                        preview();
                    }
                    case "3" -> {
                        System.out.println("Task 3. The sum of digits in the number 100! is");
                        new SumOfDigits().run();
                        preview();
                    }
                    case "0" -> System.exit(0);
                    default -> preview();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize Input Data if it does not exist.
     */
    private static void createTaskDataIfDoesNotExist() {
        createDirectories(FILE_DIR);
        write(FILE_DIR + RoadPrice.INPUT_FILE, """
                1
                4
                gdansk
                2
                2 1
                3 3
                bydgoszcz
                3
                1 1
                3 1
                4 4
                torun
                3
                1 3
                2 1
                4 1
                warszawa
                2
                2 4
                3 1
                2
                gdansk warszawa
                bydgoszcz warszawa""", false);
    }

    private static void preview() {
        System.out.println("""
                Enter 1 to run Task 1
                Enter 2 to run Task 2
                Enter 3 to run Task 3
                Enter 0 to exit:\s""");
    }
}
