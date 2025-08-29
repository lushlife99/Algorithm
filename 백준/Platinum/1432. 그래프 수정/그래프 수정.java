import java.io.*;
import java.util.*;

/**
 * boj 1432 그래프 수정
 * 위상정렬
 */
public class Main {

    static int N;
    static List<List<Integer>> revEdges; // 역간선
    static int[] outdeg; // 출차수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        revEdges = new ArrayList<>(N);
        outdeg = new int[N];

        for (int i = 0; i < N; i++) {
            revEdges.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (line[j] == '1') {
                    outdeg[i]++;
                    revEdges.get(j).add(i);
                }
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < N; i++) {
            if (outdeg[i] == 0) pq.offer(i);
        }

        int[] ans = new int[N];
        int num = N;

        while (!pq.isEmpty()) {
            int u = pq.poll();
            ans[u] = num--;

            for (int parent : revEdges.get(u)) {
                outdeg[parent]--;
                if (outdeg[parent] == 0) pq.offer(parent);
            }
        }

        if (num != 0) {
            bw.write("-1");
        } else {
            for (int i = 0; i < N; i++) {
                bw.write(ans[i] + (i == N - 1 ? "" : " "));
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
