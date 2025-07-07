import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        String[] tokens = bf.readLine().split(" ");
        int[] arr = new int[tokens.length];

        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(tokens[i]);

        long[][] dp = new long[N-1][21];
        dp[0][arr[0]] = 1;

        for (int i = 1; i < N-1; i++) {
            for (int j = 0; j < 21; j++) {
                if(dp[i-1][j] != 0) {
                    int minus = j - arr[i];
                    int plus = j + arr[i];

                    if (0 <= minus && minus <= 20)
                        dp[i][minus] += dp[i - 1][j];
                    if (0 <= plus && plus <= 20)
                        dp[i][plus] += dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N-2][arr[N-1]]);
    }
}
