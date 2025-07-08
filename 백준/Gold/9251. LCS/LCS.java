import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static char[] str1;
    static char[] str2;

    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();
        dp = new int[str1.length][str2.length];
        for (int i = 0; i < str1.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(getLCS(str1.length - 1, str2.length - 1));

    }

    static int getLCS(int x, int y) {

        if(x == -1 || y == -1) {
            return 0;
        }

        if(dp[x][y] == -1) {
            dp[x][y] = 0;

            if(str1[x] == str2[y]) {
                dp[x][y] = getLCS(x - 1, y - 1) + 1;
            }
            else {
                dp[x][y] = Math.max(getLCS(x - 1, y), getLCS(x, y - 1));
            }
        }
        return dp[x][y];
    }
}