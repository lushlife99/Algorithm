import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * boj 13172 Σ
 * 수학
 */


public class Main {
    static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine().trim());
        long answer = 0;

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long N = Long.parseLong(st.nextToken());
            long S = Long.parseLong(st.nextToken());

            long g = gcd(N, S);
            N /= g;
            S /= g;

            long invN = modPow(N, MOD - 2);
            long term = (S % MOD) * invN % MOD;

            answer = (answer + term) % MOD;
        }

        System.out.println(answer);
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    static long modPow(long a, long e) {
        long result = 1;
        long base = a % MOD;

        while (e > 0) {
            if ((e & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            e >>= 1;
        }
        return result;
    }
}
