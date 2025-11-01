import java.io.*;
import java.util.*;

/**
 * boj 2629 양팔저울
 * dp, 0-1 배낭문제
 *
 * 담을 수 없는 경우
 * dp[i][j] = dp[i-1][j]
 *
 * 담을 수 있는 경우
 * dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W] + V)
 */

/**
 *
 * 반례 생각하기
 */

public class Main {
    static int N, M, question, max=15000, arr[];
    static boolean dp[][];

    public static void main(String [] args) throws IOException {

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        N= Integer.parseInt(br.readLine());
        arr= new int[N+1];
        dp= new boolean[31][max+1];

        StringTokenizer st= new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            arr[i]= Integer.parseInt(st.nextToken());
        }

        find_dp(0,0);

        StringBuilder sb= new StringBuilder();
        M= Integer.parseInt(br.readLine());
        st= new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            question= Integer.parseInt(st.nextToken());
            if(question>15000)  sb.append("N ");
            else sb.append(dp[N][question]?"Y ":"N ");
        }
        System.out.println(sb);
        br.close();
    }

    public static void find_dp(int idx, int weight) {
        if(dp[idx][weight]) return;
        dp[idx][weight]=true;
        if(idx==N) return;

        find_dp(idx+1, weight+arr[idx+1]);
        find_dp(idx+1, weight);
        find_dp(idx+1, Math.abs(weight-arr[idx+1]));
    }
}

