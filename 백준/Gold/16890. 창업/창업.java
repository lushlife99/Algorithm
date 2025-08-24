import java.io.*;
import java.util.*;

/**
 * boj 16890 창업
 * 그리디
 */


public class Main {
    static int N;
    static char[] A, B, selA, selB;
    static int aL, aR, bL, bR, ansL, ansR;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();
        N = A.length;
        char[] answer = new char[N];
        if (N == 1) {
            System.out.println(A[0]);
            return;
        }
        Arrays.sort(A);
        Arrays.sort(B);
        selA = Arrays.copyOfRange(A, 0, (N + 1) / 2);
        selB = new char[N / 2];
        for (int i = 0; i < N / 2; i++) selB[i] = B[N - 1 - i];
        aL = bL = 0;
        aR = selA.length - 1;
        bR = selB.length - 1;
        ansL = 0;
        ansR = N - 1;
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                if (aL <= aR && bL <= bR && selA[aL] < selB[bL]) answer[ansL++] = selA[aL++];
                else answer[ansR--] = selA[aR--];
            } else {
                if (aL <= aR && bL <= bR && selA[aL] < selB[bL]) answer[ansL++] = selB[bL++];
                else answer[ansR--] = selB[bR--];
            }
        }
        System.out.println(new String(answer));
    }
}
