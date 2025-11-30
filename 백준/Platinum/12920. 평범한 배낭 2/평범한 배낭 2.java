import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * boj 12920 평범한 배낭 2
 * knapsack, 이진 분할
 *
 * dp[weight] = 만족도
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<int[]> items = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int k = 1;
            while(k <= c){
                items.add(new int[]{w*k, v*k});
                c -= k;
                k <<= 1;
            }
            if(c > 0) items.add(new int[]{w*c, v*c});
        }

        int[] dp = new int[M+1];
        for(int[] item : items){
            int w = item[0], val = item[1];
            for(int j = M; j >= w; j--){
                dp[j] = Math.max(dp[j], dp[j-w] + val);
            }
        }

        int ans = 0;
        for(int i=0;i<=M;i++) ans = Math.max(ans, dp[i]);
        System.out.println(ans);
    }
}

