import java.io.*;
import java.util.*;

/**
 * BOJ 2157
 * graph, dp
 *
 * dp[N][M] = M번의 이동으로 N까지 갔을 때 최대 기내식의 점수
 *
 * 이런 느낌
 * dp[current][M] = dp[prev][M-1] + value
 */

public class Main {
    static int N, M, K;
    static int[][] dp;
    static List<Edge>[] graph;

    static class Edge {
        int to, value;
        Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        K = Integer.parseInt(split[2]);

        dp = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) Arrays.fill(dp[i], -1);
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            split = br.readLine().split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);
            int value = Integer.parseInt(split[2]);
            if (from < to) {
                graph[from].add(new Edge(to, value));
            }
        }

        dp[1][1] = 0;

        for (int cnt = 1; cnt < M; cnt++) {
            for (int city = 1; city <= N; city++) {
                if (dp[city][cnt] == -1) continue;
                for (Edge e : graph[city]) {
                    dp[e.to][cnt + 1] = Math.max(dp[e.to][cnt + 1], dp[city][cnt] + e.value);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= M; i++) {
            answer = Math.max(answer, dp[N][i]);
        }
        System.out.println(answer);
    }
}
