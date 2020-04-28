package q009;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MyTask implements Runnable {
    static List<Integer> list = new ArrayList<>();

    @Override
    public void run() {
        int[] primeNumberArray = calc();
        print(primeNumberArray);
    }

    /**
     * 素因数分解の計算
     *
     * @return
     */
    public static int[] calc() {
        int n = Q009.getValue();
        int m = Q009.getValue();
        list = new ArrayList<>();
        BigInteger bi = null;
        int i = 2;
        while (!(n <= 1 || m <= i)) {
            if ((n % i) == 0) {
                n = n / i;
                list.add(i);
            } else {
                bi = new BigInteger(String.valueOf(i));
                i = bi.nextProbablePrime().intValue();
            }
        }
        if (m == n) {
            System.out.println(n);
        }
        // 取り出した素数を配列に入れ直す
        int[] primeNumberArray = new int[list.size()];
        for (int j = 0; j < list.size(); j++) {
            primeNumberArray[j] = list.get(j);
        }
        return primeNumberArray;
    }

    /**
     * 素因数分解の結果を出力
     * @param int[] primeNumberArray
     */
    static void print(int[] primeNumberArray) {
        System.out.print(Q009.getValue() + ": ");
        for (int i = 0; i < primeNumberArray.length - 1; i++) {
            System.out.print(primeNumberArray[i] + ",");
        }
        System.out.println(primeNumberArray[primeNumberArray.length - 1]);
    }
}