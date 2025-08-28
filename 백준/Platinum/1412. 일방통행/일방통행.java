import java.io.*;
import java.util.*;

/**
 * boj 1412 일방통행
 * 위상정렬
 *
 * edge case
 * - 자기 자신을 가르키는 간선
 */

public class Main {

    static int N;
    static char[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        List<List<Integer>> edges = new ArrayList<>();
        int[] degree = new int[N];

        for (int i = 0; i < N; i++) {
            edges.add(new ArrayList<>());
            for (int j = 0; j < N; j++) {

                if (i == j && arr[i][j] == 'Y') {
                    System.out.println("NO");
                    return;
                }

                if (arr[i][j] == 'Y' && arr[i][j] != arr[j][i]) {
                    edges.get(i).add(j);
                    degree[j] += 1;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : edges.get(u)) {
                degree[v]--;

                if (degree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (degree[i] != 0) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");

    }
}
