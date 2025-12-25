import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * boj 1086 박성원
 * dp
 */


public class Main {

    static int N;
    static int K;
    static String[] nums;
    static int[] mod;
    static int[] pow10;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new String[N];
        mod = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = br.readLine();
        }

        K = Integer.parseInt(br.readLine());
        BigInteger bi;
        BigInteger bk = BigInteger.valueOf(K);
        for (int i = 0; i < N; i++) {
            bi = new BigInteger(nums[i]);
            mod[i] = Integer.parseInt(bi.mod(bk).toString());
        }

        pow10 = new int[51];
        pow10[0] = 1%K;
        for (int i = 1; i <= 50; i++) {
            pow10[i] = pow10[i-1] * 10 % K;
        }

        dp = new long[1<<N][K];
        for (int i = 0; i < 1 << N; i++) {
            Arrays.fill(dp[i], -1);
        }

        long child = setDp(0, 0);
        long mother = factorial(N);     // 전체 경우의 수 N!

        long g = gcd(child, mother);
        System.out.println((child / g) + "/" + (mother / g));

    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    static long factorial(int n) {
        long res = 1;
        for (int i = 2; i <= n; i++) {
            res*=i;
        }
        return res;
    }

    static long setDp(int bitmask, int curMod) {
        if (dp[bitmask][curMod] != -1) return dp[bitmask][curMod];

        if (bitmask == (1<<N)-1) {
            return dp[bitmask][curMod] = curMod == 0 ? 1 : 0;
        }

        long res = 0;
        for (int i = 0; i < N; i++) {
            int idx = 1 << i;
            if ((bitmask & idx) == 0) {
                int nextMod = (curMod * pow10[nums[i].length()] + mod[i]) % K;
                res += setDp(bitmask|idx, nextMod);
            }
        }

        return dp[bitmask][curMod] = res;
    }

}
