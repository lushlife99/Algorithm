import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 10836 여왕벌
 * 시뮬레이션
 */


public class Main {

    static int M, N;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[M][M];
        for (int i = 0; i < M; i++) {
            Arrays.fill(arr[i], 1);
        }

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int zeroCnt = Integer.parseInt(st.nextToken());
            int oneCnt = Integer.parseInt(st.nextToken());
            int twoCnt = Integer.parseInt(st.nextToken());
            addBoundary(zeroCnt, oneCnt, twoCnt);
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < M; j++) {
                int max = 0;
                max = Math.max(max, arr[i-1][j]);
                max = Math.max(max, arr[i-1][j-1]);
                max = Math.max(max, arr[i][j-1]);

                arr[i][j] = Math.max(max, arr[i][j]);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void addBoundary(int z, int o, int t) {
        for (int i = M - 1; i >= 0; i--) {
            if (z > 0) {
                z--;
            } else if (o > 0) {
                arr[i][0] += 1;
                o--;
            } else {
                arr[i][0] += 2;
            }
        }

        for (int i = 1; i < M; i++) {
            if (z > 0) {
                z--;
            } else if (o > 0) {
                arr[0][i] += 1;
                o--;
            } else {
                arr[0][i] += 2;
            }
        }
    }
}