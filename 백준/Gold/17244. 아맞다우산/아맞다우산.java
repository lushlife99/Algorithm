import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 17244 아맞다우산
 *
 * 순열, 최단거리
 *
 * # - 벽
 * . - 비어있는 곳
 * S - 현재위치
 * E - 나가는 문의 위치
 * X - 물건 종류
 *
 * 모든 위치 파악
 * 물건 챙기는 조합 구해서 최소시간 구함
 * 마지막 물건 챙기는 시간에서 나갈 때까지의 최소 시간 구함
 */

public class Main {

    static int N;
    static int M;
    static char[][] arr;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new char[N][M];

        Map<Character, List<int[]>> charMap = new HashMap<>();
        charMap.put('S', new ArrayList<>());
        charMap.put('E', new ArrayList<>());
        charMap.put('X', new ArrayList<>());
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == '#' || arr[i][j] == '.') continue;

                charMap.get(arr[i][j]).add(new int[]{i, j});
            }
        }

        int[] start = charMap.get('S').get(0);
        int[] end = charMap.get('E').get(0);

        // 1. 물건 가져올 경로 조합 구하기
        List<List<Integer>> permutations = getPermutations(1, charMap.get('X').size(), new boolean[charMap.get('X').size()+1], new ArrayList<>());

        // 다익스트라
        List<int[]> nodes = new ArrayList<>();
        nodes.add(start);
        nodes.addAll(charMap.get('X'));
        nodes.add(end);

        int[][] distance = new int[nodes.size()][nodes.size()];

        for (int i = 0; i < nodes.size(); i++) {
            int[] from = nodes.get(i);
            Queue<int[]> q = new LinkedList<>(java.util.List.of(from));
            int[][] visited = new int[N][M];
            for (int j = 0; j < N; j++) Arrays.fill(visited[j], Integer.MAX_VALUE);
            visited[from[0]][from[1]] = 0;

            while (!q.isEmpty()) {
                int[] current = q.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = current[0] + dx[j];
                    int ny = current[1] + dy[j];

                    if (0 > nx || 0 > ny || N <= nx || M <= ny) continue;
                    if (arr[nx][ny] == '#') continue;

                    if (visited[nx][ny] > visited[current[0]][current[1]] + 1) {
                        visited[nx][ny] = visited[current[0]][current[1]] + 1;
                        q.add(new int[]{nx, ny});
                    }
                }
            }

            for (int j = 0; j < nodes.size(); j++) {
                int[] to = nodes.get(j);
                distance[i][j] = visited[to[0]][to[1]];
            }
        }

        int answer = Integer.MAX_VALUE;

        if (charMap.get('X').size() == 0) {
            answer = distance[0][1];
        } else {
            for (List<Integer> perm : permutations) {
                int res = distance[0][perm.get(0)];

                for (int i = 0; i < perm.size()-1; i++) {
                    res += distance[perm.get(i)][perm.get(i+1)];
                }
                res += distance[perm.get(perm.size()-1)][distance.length-1];

                answer = Math.min(answer, res);
            }
        }

        System.out.println(answer);
    }

    private static List<List<Integer>> getPermutations(int start, int end, boolean[] visited, List<Integer> current) {
        List<List<Integer>> res = new ArrayList<>();
        if (current.size() == end) {
            res.add(new ArrayList<>(current));
            return res;
        }

        for (int i = start; i <= end; i++) {
            if (!visited[i]) {
                current.add(i);
                visited[i] = true;
                res.addAll(getPermutations(start, end, visited, current));
                visited[i] = false;
                current.remove(current.size()-1);
            }

        }

        return res;
    }

}