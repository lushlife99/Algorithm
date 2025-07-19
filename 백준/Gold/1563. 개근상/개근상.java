import java.io.*;
import java.security.Principal;

/**
 * dp[일][지각 횟수][연속 결석 수] = 개근상을 받을 수 있는 출결 정보
 * dp[3][1][0] = 3일동안 한번 지각해서 개근상을 받을 수 있는 방법 수
 * 지각 횟수 == 0 sum dp[2][0][0~2] 2일차에서 무조건 지각해야됨
 * 지각 횟수 == 1 dp[2][1][0~2] 2일차에서 지각하면 안됨.
 * dp[2][1][0] -> 출석만 가능
 * dp[2][1][1] -> 출석만 가능
 * dp[2][1][1] => 출석만 가능
 * <p>
 * dp[2][0][k] = dp[1][0][k-1] + dp[1][0][0~2]
 */

public class Main {

    static int N;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][2][3];

        bottomUp();
        int answer = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                answer = (answer + dp[N][i][j]) % 1000000;
            }
        }

        System.out.println(answer);
    }

    private static void bottomUp() {

        dp[1][0][0] = 1;
        dp[1][1][0] = 1;
        dp[1][0][1] = 1;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j == 0) { //지각 X
                        if (k == 0) { // 지각 X 결석 X
                            for (int l = 0; l < 3; l++) {
                                dp[i][0][0] = (dp[i][0][0] + dp[i - 1][0][l]) % 1000000;
                            }
                        } else { //지각 X 결석 O
                            dp[i][0][k] = (dp[i][0][k] + dp[i - 1][0][k - 1]) % 1000000;
                        }
                    } else { //지각 O, j == 1

                        if (k == 0) {
                            for (int l = 0; l < 3; l++) {
                                dp[i][1][0] = (dp[i][1][0] + dp[i - 1][0][l]) % 1000000; // 지각 안했던 출석부에서 지각하는 경우
                                dp[i][1][0] = (dp[i][1][0] + dp[i - 1][1][l]) % 1000000; // 지각 했던 출석부에서 출석하는 경우
                            }
                        } else {
                            dp[i][1][k] = (dp[i][1][k] + dp[i - 1][1][k-1]) % 1000000; // 결석하는 경우
                        }
                    }
                }
            }
        }
    }

}
