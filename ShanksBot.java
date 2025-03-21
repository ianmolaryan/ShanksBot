import java.math.*;
import java.util.*;

public class ShanksBot {
    public static int countRepeatingDecimalLength(int denom) {
        int precision = denom * 2;  // Higher precision to capture large repeating sequences
        BigDecimal numerator = BigDecimal.ONE;
        BigDecimal denominator = new BigDecimal(denom);
        MathContext mc = new MathContext(precision);
        String result = numerator.divide(denominator, mc).toString();
        String decimalPart = result.split("\\.")[1];

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
        System.out.print("Enter a prime number: ");
        int prime = sc.nextInt();
        System.out.println("Length of repeating part: " + countRepeatingDecimalLength(prime));
    }
}
