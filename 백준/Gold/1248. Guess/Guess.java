import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 1248 guess
 */


public class Main {
    static int N, board[][], num[], sum[];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];
        num = new int[N + 1];
        sum = new int[N + 1];

        String str = br.readLine();
        int index = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                char c = str.charAt(index++);

                if (c == '0') board[i][j] = 0;
                else if (c == '+') board[i][j] = 1;
                else if (c == '-') board[i][j] = -1;
            }
        }

        dfs(1);
    }

    static void dfs(int count) {

        if (!check(count - 1)) return;

        if (count > N) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < count; i++)
                sb.append(num[i]).append(" ");

            System.out.println(sb);
            System.exit(0);
        }

        int sign = board[count][count];

        if(sign == 0) {
            num[count] = 0;
            sum[count] = sum[count - 1] + 0;
            dfs(count + 1);
        }
        else {
            for (int i = 1; i <= 10; i++) {
                num[count] = sign * i;
                sum[count] = sum[count - 1] + sign * i ;
                dfs(count + 1);
            }
        }

    }

    private static boolean check(int count) {

        for (int i = 1; i < count; i++)
            if (Integer.signum(sum[count] - sum[i - 1]) != board[i][count])
                return false;
        return true;
    }
}
