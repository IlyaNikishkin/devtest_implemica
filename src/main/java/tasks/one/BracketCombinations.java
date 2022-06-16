package tasks.one;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Task2: Count the number of expressions containing n pairs of parentheses which are correctly matched.
 * Algorithm: Using of the recurrence relations (Catalan numbers):
 * C[0] = 1, C[n] = Sum[i=0 to n] C[i] * C[n-i] for n >= 0.
 */
public class BracketCombinations {

    public void run(BufferedReader reader) throws IOException {
        int n;
        try {
            n = Integer.parseInt(reader.readLine());
            if (n > 0) {
                System.out.println(getCatalanNumber(n));
            } else {
                System.out.println("Invalid input");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }

    /**
     * Method calculates the Catalan Numbers.
     *
     * @param number - the entered number.
     * @return 1 for number = 0, C[n] for number > 0.
     */
    private int getCatalanNumber(int number) {
        int sum = 0;
        if (number == 0) {
            return 1;
        }
        for (int i = 0; i < number; i++) {
            sum += getCatalanNumber(i) * getCatalanNumber((number - 1) - i);
        }
        return sum;
    }
}
