import java.io.*;
import java.util.*;

/**
 * BOJ 2931 가스관
 * 구현
 */

public class Main {

    static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // up, down, left, right
    static Map<Character, List<Integer>> pipeDirectionMap;
    static int N, M;
    static char[][] arr;

    static {
        pipeDirectionMap = Map.of(
                '|', List.of(0, 1),
                '-', List.of(2, 3),
                '+', List.of(0, 1, 2, 3),
                '1', List.of(1, 3),
                '2', List.of(0, 3),
                '3', List.of(0, 2),
                '4', List.of(1, 2)
        );
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] target = null;

        arr = new char[N][M];

        for (int i = 0; i < N; i++) arr[i] = br.readLine().toCharArray();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char c = arr[i][j];
                if (c != '.' && c != 'M' && c != 'Z') { // 파이프일 경우
                    List<Integer> directions = pipeDirectionMap.get(c);
                    for (Integer idx : directions) {
                        int nx = i + d[idx][0];
                        int ny = j + d[idx][1];

                        if (arr[nx][ny] == '.') {
                            target = new int[]{nx, ny};
                            break;
                        }
                    }
                }
            }

            if (target != null) break;
        }
        char answer = ' ';
        List<Integer> emptyDirections = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int nx = target[0] + d[i][0];
            int ny = target[1] + d[i][1];

            if (nx < 0 || ny < 0 || nx == N || ny == M) continue;
            if (arr[nx][ny] == '.') continue;

            if ((arr[nx][ny] == 'M' || arr[nx][ny] == 'Z')) {
                if (!hasPipeNearby(nx, ny)) {
                    emptyDirections.add(i);
                }
                continue;
            }

            List<Integer> directions = pipeDirectionMap.get(arr[nx][ny]);
            for (Integer idx : directions) {
                if (nx + d[idx][0] == target[0] && ny + d[idx][1] == target[1]) {
                    emptyDirections.add(i);
                    break;
                }
            }
        }
        Collections.sort(emptyDirections);
        int hash = Objects.hashCode(emptyDirections);
        for (Character c : pipeDirectionMap.keySet()) {
            if (hash == Objects.hashCode(pipeDirectionMap.get(c))) {
                answer = c;
                break;
            }
        }

        System.out.printf("%d %d %c", target[0] + 1, target[1] + 1, answer);
    }

    static private boolean hasPipeNearby(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + d[i][0];
            int ny = y + d[i][1];

            if (nx < 0 || ny < 0 || nx == N || ny == M) continue;
            char c = arr[nx][ny];
            if (pipeDirectionMap.containsKey(c)) return true;

        }

        return false;
    }
}
