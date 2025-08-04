import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 16985
 * 판을 쌓는 순서 5! = 120
 * 회전 횟수 4*4*4*4*4 = 1024번
 * 입구 꼭짓점 갯수 = 8개 -> 근데 어차피 회전하니까 똑같아서 고려 안해도 됨.
 * <p>
 * 약 120 * 1024개의 조합
 */

public class Main {

    static int[] ROTATE_STATE = {0, 1, 2, 3};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][][] arr = new int[5][5][5];
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        List<List<Integer>> orderCombinations = getPanelCombination(5, new boolean[5], new ArrayList<>());
        List<List<Integer>> rotateCombinations = getRotateCombination(5, new ArrayList<>());

        for (List<Integer> orderComb : orderCombinations) {
            int[][][] currentMaze = new int[5][5][5];

            for (List<Integer> rotateComb : rotateCombinations) {
                for (int i = 0; i < 5; i++) {
                    currentMaze[i] = rotate(arr[orderComb.get(i)], rotateComb.get(i));
                }

                int res = bfs(new int[]{0, 0, 0}, currentMaze);
                answer = Math.min(res, answer);

            }
        }

        answer = answer == Integer.MAX_VALUE ? -1 : answer;
        System.out.println(answer);
    }

    private static int bfs(int[] start, int[][][] maze) {
        // 0 -> 4 // 4->0
        if (maze[start[0]][start[1]][start[2]] == 0) return Integer.MAX_VALUE;
        boolean[][][] visited = new boolean[5][5][5];
        visited[start[0]][start[1]][start[2]] = true;
        int[] end = new int[]{4 - start[0], 4 - start[1], 4 - start[2]};
        int[] dx = {1, -1, 0, 0, 0, 0};
        int[] dy = {0, 0, 1, -1, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{start[0], start[1], start[2], 0});

        while (!q.isEmpty()) {
            int[] current = q.poll();

            if (current[0] == end[0] && current[1] == end[1] && current[2] == end[2]) {
                return current[3];
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                int nz = current[2] + dz[i];

                if (nx < 0 || ny < 0 || nz < 0 || nx > 4 || ny > 4 || nz > 4) continue;
                if (maze[nx][ny][nz] == 0 || visited[nx][ny][nz]) continue;

                visited[nx][ny][nz] = true;
                q.add(new int[]{nx, ny, nz, current[3] + 1});
            }
        }

        return Integer.MAX_VALUE;
    }

    // 회전
    private static int[][] rotate(int[][] prev, int rotateState) {
        // rotateState == 1 오른쪽 90도
        // 오른쪽 90도 NRow = PCol, NCol = Size-1-NRow
        int[][] newPanel = new int[5][5];

        if (rotateState == 0) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    newPanel[i][j] = prev[i][j];
                }
            }
        } else if (rotateState == 1) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    newPanel[i][j] = prev[j][4 - i];
                }
            }
        }

        // 180도
        else if (rotateState == 2) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    newPanel[i][j] = prev[4 - i][4 - j];
                }
            }
        }

        // 270도
        // 3,3 -> 1,3 / 4,2 -> 2,4 // 2,3 -> 1,2 // 4,4 -> 0,4
        else {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    newPanel[i][j] = prev[4 - j][i];
                }
            }
        }

        return newPanel;
    }

    private static List<List<Integer>> getRotateCombination(int size, List<Integer> current) {
        List<List<Integer>> res = new ArrayList<>();
        if (current.size() == size) {
            res.add(new ArrayList<>(current));
            return res;
        }

        for (int i = 0; i < 4; i++) {
            current.add(i);
            res.addAll(getRotateCombination(size, current));
            current.remove(current.size() - 1);
        }

        return res;
    }

    // 조합
    private static List<List<Integer>> getPanelCombination(int size, boolean[] visited, List<Integer> current) {
        List<List<Integer>> res = new ArrayList<>();
        if (current.size() == size) {
            res.add(new ArrayList<>(current));
            return res;
        }

        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                visited[i] = true;
                current.add(i);
                res.addAll(getPanelCombination(size, visited, current));
                visited[i] = false;
                current.remove(current.size() - 1);
            }
        }

        return res;
    }
}