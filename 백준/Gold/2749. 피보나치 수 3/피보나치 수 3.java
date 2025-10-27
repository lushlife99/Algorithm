import java.io.*;
import java.util.*;

/**
 * boj 2749 피보나치 수 3
 *
 */


public class Main {

    static long[] fibo;
    static final int pisano = 15 * 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        N %= pisano;
        fibo = new long[(int) N + 1];
        fibo[0] = 0;
        fibo[1] = 1;
        for (int i = 2; i <= N; i++) {
            fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 1000000;
        }
        System.out.println(fibo[(int) N]);
    }

}

