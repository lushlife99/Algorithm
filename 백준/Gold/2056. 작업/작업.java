import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BOJ 2056
 * dp[A] = A 작업을 완료하기 위한 최소 시간
 * dp[A] = Math.max(dp[0~K]) + arr[A]
 *
 * 시뮬
 * N * K = 10^4 * 10^2
 */

public class Main {

    static int N;
    static int[] dp;
    static int[] time;
    static Map<Integer, List<Integer>> parents = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        time = new int[N+1];
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split(" ");
            time[i+1] = Integer.parseInt(split[0]);
            int K = Integer.parseInt(split[1]);
            parents.put(i+1, new ArrayList<>());

            for (int j = 0; j < K; j++) {
                parents.get(i+1).add(Integer.parseInt(split[j+2]));
            }
        }

        int answer = 0;

        for (int i = 1; i <= N; i++) {

            for (int key : parents.get(i)) {
                dp[i] = Math.max(dp[key], dp[i]);
            }
            dp[i] += time[i];
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }

}
