import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 1017 소수 쌍
 * 이분 매칭
*/

public class Main {

    private static int N;
    private static int[] arr;
    private static List<Integer>[] edges;
    private static int[] connected;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N+1];
        visited = new boolean[N+1];
        connected = new int[N+1];

        edges = IntStream.rangeClosed(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i+1; j <= N; j++) {
                if (isPrime(arr[i] + arr[j])) {
                    edges[i].add(j);
                    edges[j].add(i);
                }
            }
        }

        List<Integer> answers = new ArrayList<>();
        for (int i = 0; i < edges[1].size(); i++) {
            int p = edges[1].get(i);
            Arrays.fill(connected, 0);
            connected[1] = p;
            connected[p] = 1;
            int cnt = 1;
            for (int j = 2; j <= N; j++) {
                Arrays.fill(visited, false);
                if (connected[j] == 0) {
                    if (dfs(j)) {
                        cnt++;
                    }
                    else break;
                }
            }

            if (cnt == N/2) {
                answers.add(arr[connected[1]]);
            }
        }

        if (answers.isEmpty()) {
            System.out.println(-1);
        } else {
            Collections.sort(answers);
            StringBuilder sb = new StringBuilder();
            for (Integer answer : answers) {
                sb.append(answer).append(" ");
            }

            System.out.print(sb);
        }
    }

    private static boolean dfs(int x) {
        for (int i = 0; i < edges[x].size(); i++) {
            int p = edges[x].get(i);
            if (visited[p]) continue;
            visited[p] = true;
            if (p == 1 || connected[p] == 1) continue;

            if (connected[p] == 0 || dfs(connected[p])) {
                connected[p] = x;
                connected[x] = p;
                return true;
            }
        }

        return false;
    }


    private static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

}
