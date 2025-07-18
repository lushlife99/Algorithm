import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * dp 문제
 * dp[person] = Min(cost)
 * <p>
 * info cost a, person b
 * dp[person] = Min(dp[person], dp[person-b] + a)
 */
public class Main {

    static int C;
    static int N;
    static List<Info> infos = new ArrayList<>();
    static int[] dp;

    static class Info {
        int cost;
        int person;

        public Info(int cost, int person) {
            this.cost = cost;
            this.person = person;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        C = Integer.parseInt(split[0]);
        N = Integer.parseInt(split[1]);
        dp = new int[C + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            split = br.readLine().split(" ");
            infos.add(new Info(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }

        for (int i = 1; i <= C; i++) {
            for (Info info : infos) {
                dp[i] = Math.min(dp[i], dp[Math.max(0, i - info.person)] + info.cost);
            }
        }
        
        System.out.println(dp[C]);
    }
}