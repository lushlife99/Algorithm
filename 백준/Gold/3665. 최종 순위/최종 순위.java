import java.io.*;
import java.util.*;

/**
 * boj 3665 최종 순위
 * 위상정렬
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());

            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

            int[] indegree = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] order = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                order[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    graph.get(order[i]).add(order[j]);
                    indegree[order[j]]++;
                }
            }

            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (graph.get(a).contains(b)) {
                    graph.get(a).remove((Integer) b);
                    indegree[b]--;
                    graph.get(b).add(a);
                    indegree[a]++;
                } else {
                    graph.get(b).remove((Integer) a);
                    indegree[a]--;
                    graph.get(a).add(b);
                    indegree[b]++;
                }
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                if (indegree[i] == 0) q.offer(i);
            }

            StringBuilder sb = new StringBuilder();
            boolean certain = true;
            boolean cycle = false;

            for (int i = 0; i < n; i++) {
                if (q.isEmpty()) {
                    cycle = true;
                    break;
                }
                if (q.size() > 1) {
                    certain = false;
                }

                int cur = q.poll();
                sb.append(cur + " ");

                for (int next : graph.get(cur)) {
                    indegree[next]--;
                    if (indegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }

            if (cycle) {
                bw.write("IMPOSSIBLE\n");
            } else if (!certain) {
                bw.write("?\n");
            } else {
                bw.write(sb.toString().trim() + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
