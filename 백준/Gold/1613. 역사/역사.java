import java.io.*;
import java.util.*;
import java.util.stream.*;


/**
 * boj 1613 역사
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[][] adj = new boolean[n+1][n+1];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a][b] = true;
        }

        for (int mid = 1; mid <= n; mid++) {
            for (int from = 1; from <= n; from++) {
                if (!adj[from][mid]) continue;
                for (int to = 1; to <= n; to++) {
                    if (adj[mid][to]) adj[from][to] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int s = Integer.parseInt(br.readLine());

        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (adj[a][b]) sb.append("-1\n");
            else if (adj[b][a]) sb.append("1\n");
            else sb.append("0\n");
        }

        System.out.print(sb);
    }
}
