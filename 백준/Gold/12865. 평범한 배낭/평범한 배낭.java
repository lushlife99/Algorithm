import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, K;
    static Stuff[] stuffs;
    static Integer[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        K = Integer.parseInt(split[1]);
        stuffs = new Stuff[N];
        dp = new Integer[N + 1][K + 1];

        for (int i = 0; i < N; i++) {
            split = br.readLine().split(" ");
            stuffs[i] = new Stuff(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }

        System.out.println(dfs(0, 0));
    }

    public static int dfs(int idx, int w) {
        if (idx == N) return 0;
        if (dp[idx][w] != null) return dp[idx][w];

        int notPick = dfs(idx + 1, w);

        int pick = 0;
        if (w + stuffs[idx].weight <= K) {
            pick = dfs(idx + 1, w + stuffs[idx].weight) + stuffs[idx].value;
        }

        return dp[idx][w] = Math.max(notPick, pick);
    }

    static class Stuff {
        int weight, value;
        Stuff(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
