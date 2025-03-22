import java.math.*;
import java.util.*;

public class ShanksBot 
{
    
    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0)
                return false;
        }
        return true;
    }
    
    public static int countRepeatingDecimalLength(int denom, int precision) {
        BigDecimal numerator = BigDecimal.ONE;
        BigDecimal denominator = new BigDecimal(denom);
        MathContext mc = new MathContext(precision);
        String result = numerator.divide(denominator, mc).toString();
        String[] parts = result.split("\\.");
        if (parts.length < 2) return 0;
        String decimalPart = parts[1];
        
        for (int length = 1; length <= decimalPart.length() / 2; length++) {
            String pattern = decimalPart.substring(0, length);
            boolean isRepeating = true;
            for (int start = length; start + length <= decimalPart.length(); start += length) {
                if (!decimalPart.substring(start, start + length).equals(pattern)) {
                    isRepeating = false;
                    break;
                }
            }
            if (isRepeating) {
                return length;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a prime number:           ");
        int prime = sc.nextInt();
        
        if (!isPrime(prime)) {
            System.out.println(prime + " is not a prime number. Exiting.");
            return;
        }
        
        // Choose a precision high enough for the repeating cycle; using denom * 2 for demonstration.
        int precision = prime * 2;
        
        long startTime = System.nanoTime();
        int repeatingLength = countRepeatingDecimalLength(prime, precision);
        long endTime = System.nanoTime();
        
        double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
        
        System.out.println("Prime number:                   " + prime);
        System.out.println("Precision used:                 " + precision);
        System.out.println("Length of repeating part:       " + repeatingLength);
        System.out.println("Calculation execution time:     " + durationInSeconds + " seconds");
    }
}
