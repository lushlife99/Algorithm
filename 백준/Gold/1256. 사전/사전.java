import java.io.*;

/**
 * BOJ 1256
 *
 * O(N) = 10억..
 * 모르겠어서 정답 봄
 * dp[N][M] = dp[N-1][M] + dp[N][M-1] = n개 m개 사용했을 때 문자열 갯수
 */

public class Main {

    static int N;
    static int M;
    static int K;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        K = Integer.parseInt(split[2]);
        dp = new int[N+1][M+1];

        if (dfs(N, M) < K) {
            System.out.println(-1);
        } else {
            makeS(N, M, K);
            System.out.println(sb.toString());
        }
    }

    private static int dfs(int a, int b) {
        if (a == 0 || b == 0) return 1;
        if (dp[a][b] != 0) return dp[a][b];

        return dp[a][b] = Math.min(dfs(a-1, b) + dfs(a, b-1), 1000000001);
    }

    private static void makeS(int a, int b, int k) {
        if(a==0) {
            for(int i=0; i<b; i++)
                sb.append("z");
            return;
        }
        if(b==0) {
            for(int i=0; i<a; i++)
                sb.append("a");
            return;
        }

        int value = dfs(a-1, b);
        if(k>value) {
            sb.append("z");
            makeS(a, b-1, k-value);
        }else {
            sb.append("a");
            makeS(a-1, b, k);
        }
    }


}
