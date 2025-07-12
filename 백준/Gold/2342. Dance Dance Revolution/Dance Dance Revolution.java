import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * dp[n][m] = min(dp[n-1][0~4])
 */

public class Main {

    static int[] arr;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        arr = new int[split.length-1];
        dp = new int[split.length-1][5][5];
        for (int i = 0; i < split.length-1; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }

        System.out.println(dfs(0, 0, 0));
    }

    private static int dfs(int idx, int l, int r) {
        if (idx == arr.length) return 0;

        if (dp[idx][l][r] != 0)
            return dp[idx][l][r];

        int next = arr[idx];

        dp[idx][l][r] = Math.min(dfs(idx+1, next,r) + getDistance(l, next), dfs(idx+1, l, next) + getDistance(r, next));

        return dp[idx][l][r];
    }

    private static int getDistance(int current, int target) {

        if (current == target) return 1;
        if (current == 0) return 2;
        if (Math.abs(current - target) == 2) return 4;
        return 3;
    }

}
