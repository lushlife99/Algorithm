import java.io.*;

/**
 * BOJ 1082
 * dp
 *
 * dp[cost] = cost로 만들 수 있는 가장 큰 방 번호
 *
 * if dp[N] > dp[N-번호가격] + 방번호
 *  dp[N] = dp[N-번호가격] + 방번호
 *
 * 예를들어 dp[7] = 1
 * dp[14] = 1 + 1 = 11
 */

public class Main {

    static int N;
    static int M;
    static int[] P;
    static String[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        P = new int[N];


        String[] split = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(split[i]);
        }
        M = Integer.parseInt(br.readLine());
        dp = new String[M+1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = "";
        }

        for (int i = 0; i < N; i++) {
            if (P[i] <= M)
                dp[P[i]] = String.valueOf(i);
        }

        for (int i = 1; i <= M; i++) { // 가격 (21)
            if (compare(dp[i], dp[i-1])) {
                dp[i] = dp[i-1];
            }
            for (int j = 0; j < N; j++) { // 방 번호(3)
                if (i - P[j] >= 0) {
                    String prev = dp[i-P[j]] + dp[P[j]];
                    if (prev.charAt(0) == '0') continue;
                    if (compare(dp[i], prev)) {
                        dp[i] = prev;
                    }

                }
            }
        }

        System.out.println(dp[M]);
    }

    static boolean compare(String s1, String s2) {
        if (s1.length() < s2.length()) return true;

        else if (s1.length() == s2.length() && s1.compareTo(s2) <= 0) return true;
        
        return false;
    }
}