import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-5-27
 */

public class TestFactorial {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int input = cin.nextInt();
        while (input != 0) {
            System.out.println(bigNumber(input));
            input = cin.nextInt();
            System.out.println(trailingZeros(input));
        }
    }

    private static int trailingZeros(int input) {
        int fiveFactors = 0;

        int quotient = input / 5;
        int i = 1;
        while (quotient != 0) {
            fiveFactors += quotient;
            i++;
            quotient = (int) (input / Math.pow(5, i));
        }
        return fiveFactors;
    }

    public static synchronized BigInteger bigNumber(int num) {
        ArrayList<BigInteger> list = new ArrayList<BigInteger>();
        list.add(BigInteger.valueOf(1));
        for (int i = list.size(); i <= num; i++) {
            BigInteger lastfact = (BigInteger) list.get(i - 1);
            BigInteger nextfact = lastfact.multiply(BigInteger.valueOf(i));
            list.add(nextfact);
        }
        return list.get(num);
    }
}
