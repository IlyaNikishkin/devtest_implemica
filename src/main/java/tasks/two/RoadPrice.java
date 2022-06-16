package tasks.two;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static tasks.two.RoadPriceFiles.*;

/**
 * Task3 description.
 * You are given a list of cities.
 * Each direct connection between two cities has its transportation cost (an integer bigger than 0).
 * The goal is to find the paths of minimum cost between pairs of cities.
 * Assume that the cost of each path (which is the sum of costs of all direct connections belonging to this path) is at most 200000.
 * The name of a city is a string containing characters a,...,z and is at most 10 characters long.2)
 */
public class RoadPrice {

    public static final String INPUT_FILE = "input.txt";
    public static final String OUTPUT_FILE = "output.txt";
    private static final int INFINITE = Integer.MAX_VALUE;
    private static int numOfCities;

    /**
     * Method reads the input file, finds the paths of minimum cost between pairs of cities,
     * writes the obtained result to the output file.
     * Params: src - the input file as array of lines,
     * output - the result data,
     * readingLine - the current reading line.
     *
     * @throws InvalidInputException – if the input file is invalid.
     */
    public void run() {
        List<String> src = new ArrayList<>();
        try {
            src = RoadPriceFiles.read(FILE_DIR + INPUT_FILE);
        } catch (IOException e) {
            System.out.println(INPUT_FILE + " cannot be read");
        }

        StringBuilder output = new StringBuilder();
        int readingLine = 1;
        int numberOfTests;
        String city;
        try {
            numberOfTests = Integer.parseInt(src.get(0));
            if (numberOfTests > 10)
                throw new InvalidInputException();
            for (int currentTest = 0; currentTest < numberOfTests; currentTest++) {
                List<Integer> result = new ArrayList<>();
                List<String> cities = new ArrayList<>();
                numOfCities = Integer.parseInt(src.get(readingLine++));
                if (numOfCities > 10000)
                    throw new InvalidInputException();
                int[][] graph = new int[numOfCities][numOfCities];
                for (int i = 0; i < numOfCities; i++) {
                    city = src.get(readingLine++);
                    if (city.length() > 10 || !city.matches("[a-zA-Z]+"))
                        throw new InvalidInputException();
                    cities.add(city);
                    int numOfNeighbours = Integer.parseInt(src.get(readingLine++));
                    for (int j = 0; j < numOfNeighbours; j++) {
                        String[] neighbours = src.get(readingLine++).split(" ");
                        int cost = Integer.parseInt(neighbours[1]);
                        if (cost > 200000) {
                            throw new InvalidInputException();
                        } else {
                            graph[i][Integer.parseInt(neighbours[0]) - 1] = cost;
                        }
                    }
                }
                if (cities.size() != numOfCities)
                    throw new InvalidInputException();
                for (int i = 0; i < numOfCities; i++) {
                    for (int j = 0; j < numOfCities; j++) {
                        if (graph[i][j] == 0) graph[i][j] = INFINITE;
                    }
                }
                for (int i = 0; i < numOfCities; i++) {
                    for (int j = 0; j < numOfCities; j++) {
                        if (graph[i][j] != graph[j][i])
                            throw new InvalidInputException();
                    }
                }
                int waysToFind = Integer.parseInt(src.get(readingLine++));
                if (waysToFind > 100)
                    throw new InvalidInputException();
                output.append("Test").append(currentTest + 1).append("\n");
                for (int i = 0; i < waysToFind; i++) {
                    String[] paths = src.get(readingLine++).split(" ");
                    int startPoint, endPoint;
                    startPoint = cities.indexOf(paths[0]);
                    endPoint = cities.indexOf(paths[1]);
                    boolean[] visited = new boolean[numOfCities];
                    result.add(findCheapestCost(startPoint, endPoint, visited, graph));
                    if (result.get(result.size() - 1) == INFINITE) {
                        output.append("End and start points are disconnected\n");
                    } else {
                        output.append(result.get(result.size() - 1)).append("\n");
                    }
                }
                if (result.size() != waysToFind)
                    throw new InvalidInputException();
            }
        } catch (NumberFormatException | InvalidInputException | IndexOutOfBoundsException e) {
            output = new StringBuilder("Invalid input data");
        } finally {
            RoadPriceFiles.write(FILE_DIR + OUTPUT_FILE, output.toString(), true);
        }
    }

    /**
     * Method returns the cheapest way between two points (cities).
     */
    private static int findCheapestCost(int startPoint, int endPoint, boolean[] visited, int[][] graph) {
        if (startPoint == endPoint)
            return 0;
        visited[startPoint] = true;
        int cost = INFINITE;
        for (int i = 0; i < numOfCities; i++) {
            if (graph[startPoint][i] != INFINITE && !visited[i]) {
                int current = findCheapestCost(i, endPoint, visited, graph);
                if (current < INFINITE) {
                    cost = Math.min(cost, graph[startPoint][i] + current);
                }
            }
        }
        visited[startPoint] = false;
        return cost;
    }
}
