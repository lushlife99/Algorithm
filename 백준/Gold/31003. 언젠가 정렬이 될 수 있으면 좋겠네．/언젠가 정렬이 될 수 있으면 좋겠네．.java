import java.io.*;
import java.util.*;

/**
 * boj 31003 언젠가 정렬이 될 수 있으면 좋겠네.
 * 위상정렬, 최대공약수
 *
 * edge case
 * - 수열의 원소가 같을 때
 */

public class Main {

    static int N;
    static int[] arr;
    static Map<Integer, List<Integer>> edgeMap = new HashMap<>();
    static Map<Integer, Integer> degree = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                int max = Math.max(arr[i], arr[j]);
                int min = Math.min(arr[i], arr[j]);
                if (gcd(max, min) != 1) {
                    edgeMap.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                    degree.put(j, degree.getOrDefault(j, 0)+1);
                }
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return arr[a] - arr[b];
        });

        for (int i = 0; i < N; i++) {
            if (!degree.containsKey(i)) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int u = pq.poll();
            bw.write(arr[u] + " ");

            if (!edgeMap.containsKey(u)) continue;

            for (int v : edgeMap.get(u)) {
                degree.put(v, degree.get(v) - 1);

                if (degree.get(v) == 0) {
                    pq.offer(v);
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();

    }


    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

}
