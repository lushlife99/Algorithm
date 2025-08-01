import java.io.*;
import java.util.*;

/**
 * BOJ 17142
 *
 *
 */

public class Main {

    static int N;
    static int M;
    static int[][] arr;
    static List<int[]> viruses = new ArrayList<>();
    static int[] dx = {1, -1 ,0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        int totalVirus = N*N;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int virus = Integer.parseInt(st.nextToken());
                if (virus == 1) totalVirus--;
                else if (virus == 2) viruses.add(new int[]{i, j});
                arr[i][j] = virus;
            }
        }

        // viruses에서 M개 뽑음
        List<List<Integer>> pick = pick(0, 0, new boolean[viruses.size()], new ArrayList<>());

        // 바이러스 활성화
        int answer = Integer.MAX_VALUE;
        for (List<Integer> pickVirus : pick) {
            int res = bfs(pickVirus, totalVirus);
            if (res != -1) {
                answer = Math.min(answer, res);
            }
        }
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else System.out.println(answer);
    }

    private static int bfs(List<Integer> pickVirus, int totalVirus) {
        int[][] distance = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        int res = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(distance[a[0]][a[1]], distance[b[0]][b[1]]);
        });

        for (Integer virus : pickVirus) {
            int[] points = viruses.get(virus);
            pq.add(points);
            distance[points[0]][points[1]] = 0;
        }

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (0 > nx || 0 > ny || nx >= N || ny >= N) continue;
                if (arr[nx][ny] == 1) continue;

                if (distance[nx][ny] > distance[current[0]][current[1]] + 1) {
                    distance[nx][ny] = distance[current[0]][current[1]] + 1;
                    pq.add(new int[]{nx, ny});
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != 1) {
                    if (arr[i][j] == 0) {
                        if (distance[i][j] == Integer.MAX_VALUE) {
                            return -1;
                        }

                        res = Math.max(res, distance[i][j]);
                    }
                }
            }
        }

        return res;
    }

    private static List<List<Integer>> pick(int start, int cnt, boolean[] visited, List<Integer> current) {
        List<List<Integer>> res = new ArrayList<>();

        if (cnt == M) {
            res.add(new ArrayList<>(current));
            return res;
        }

        for (int i = start; i < viruses.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                current.add(i);
                res.addAll(pick(i+1, cnt+1, visited, current));
                current.remove(current.size()-1);
                visited[i] = false;
            }
        }

        return res;
    }
}
