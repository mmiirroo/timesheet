import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-5-27
 */

public class Testfactorial {
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

    public static synchronized BigInteger bigNumber(int num) {//利用BigInteger类计算阶乘

        ArrayList<BigInteger> list = new ArrayList<BigInteger>();//创建集合数组
        list.add(BigInteger.valueOf(1));//往数组里添加一个数值
        for (int i = list.size(); i <= num; i++) {
            BigInteger lastfact = (BigInteger) list.get(i - 1);//获得第一个元素
            BigInteger nextfact = lastfact.multiply(BigInteger.valueOf(i));//获得下一个数组
            list.add(nextfact);
        }
        return (BigInteger) list.get(num);//返回数组中的下标为num的值
    }
}
