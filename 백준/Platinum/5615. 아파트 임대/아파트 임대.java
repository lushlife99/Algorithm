import java.io.*;
import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * boj 5615 아파트 임대
 *
 */


public class Main {

    private static final long[] bases = {2, 7, 61};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count = 0;

        for (int i = 0; i < N; i++) {
            long S = Long.parseLong(br.readLine());
            if (millerRabin(2 * S + 1)) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static boolean millerRabin(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0) return false;

        long d = n - 1;
        int s = 0;
        while (d % 2 == 0) {
            d /= 2;
            s++;
        }

        for (long a : bases) {
            if (a >= n) break;
            if (!checkComposite(n, a, d, s)) return false;
        }
        return true;
    }

    private static boolean checkComposite(long n, long a, long d, int s) {
        long x = power(a, d, n);
        if (x == 1 || x == n - 1) return true;

        for (int r = 1; r < s; r++) {
            x = (long) ((BigInteger.valueOf(x).multiply(BigInteger.valueOf(x)).remainder(BigInteger.valueOf(n))).longValue());
            if (x == n - 1) return true;
        }
        return false;
    }

    private static long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if (exp % 2 == 1) res = multiply(res, base, mod);
            base = multiply(base, base, mod);
            exp /= 2;
        }
        return res;
    }

    private static long multiply(long a, long b, long m) {
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).remainder(BigInteger.valueOf(m)).longValue();
    }
}
