package tasks.three;

import java.math.BigInteger;

/**
 * Task3: Find the sum of the digits in the number 100! (i.e. 100 factorial)
 */
public class SumOfDigits {

    private static final int N = 100;

    public void run() {
        System.out.println(sumOfDigits(getFactorial()));
    }

    /**
     * The sum of digits in a decimal number is a sum of its remainders when dividing the number by 10.
     */
    private long sumOfDigits(BigInteger number) {
        long sum = 0;
        while (number.compareTo(BigInteger.ZERO) > 0) {
            sum += number.mod(BigInteger.TEN).longValue();
            number = number.divide(BigInteger.TEN);
        }
        return sum;
    }

    /**
     * Calculating value of N = 100 factorial.
     */
    private BigInteger getFactorial() {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 2; i <= N; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
}
