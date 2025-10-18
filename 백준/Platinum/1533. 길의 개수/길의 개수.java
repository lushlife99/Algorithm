import java.io.*;
import java.util.*;

/**
 * boj 1533 길의 개수
 * 그래프
 */

public class Main {
    static final int MOD = 1000003;
    static int N, S, E;
    static long T;
    static int size;
    static int[][] base;
    static int[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken()) - 1;
        E = Integer.parseInt(st.nextToken()) - 1;
        T = Long.parseLong(st.nextToken());

        size = N * 5;
        base = new int[size][size];
        result = new int[size][size];

        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < N; j++) {
                int w = line.charAt(j) - '0';
                if (w > 0) {
                    base[i*5][j*5 + (w - 1)] = (base[i*5][j*5 + (w - 1)] + 1) % MOD;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < 4; k++) {
                base[i*5 + k + 1][i*5 + k] = 1;
            }
        }

        for (int i = 0; i < size; i++) {
            result[i][i] = 1;
        }

        matrixPower(T);

        System.out.println(result[S*5][E*5]);
    }

    static void matrixPower(long exp) {
        int[][] tmp = new int[size][size];

        while (exp > 0) {
            if ((exp & 1) == 1) {
                multiply(result, base, tmp);
                copyMatrix(tmp, result);
            }
            multiply(base, base, tmp);
            copyMatrix(tmp, base);
            exp >>= 1;
        }
    }

    static void multiply(int[][] A, int[][] B, int[][] out) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                long sum = 0;
                for (int k = 0; k < size; k++) {
                    sum += (long) A[i][k] * B[k][j];
                    if (sum >= 1L * MOD * MOD) sum %= MOD;
                }
                out[i][j] = (int)(sum % MOD);
            }
        }
    }

    static void copyMatrix(int[][] src, int[][] dst) {
        for (int i = 0; i < size; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, size);
        }
    }
}
