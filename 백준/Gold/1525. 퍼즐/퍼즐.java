import java.io.*;
import java.util.*;

/**
 * BOJ 1525 퍼즐
 * bfs
 *
 * 재방문표시는 어떻게?
 *
 */

public class Main {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Puzzle> visited = new HashSet<>();
        Queue<Puzzle> queue = new LinkedList<>();
        int[][] arr = new int[3][3];
        int[] target = new int[2];
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) {
                    target = new int[]{i, j};
                }
            }
        }
        Puzzle first = new Puzzle(arr, target, 0);
        Puzzle end = new Puzzle(
                new int[][] {{1,2,3}, {4,5,6}, {7,8,0}}, null, 0
        );
        queue.offer(first);
        visited.add(first);

        int answer = -1;

        while (!queue.isEmpty()) {
            Puzzle current = queue.poll();
            if (current.equals(end)) {
                answer = current.cost;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = current.target[0] + dx[i];
                int ny = current.target[1] + dy[i];

                if (nx < 0 || ny < 0 || nx > 2 || ny > 2) continue;

                int[][] nextPuzzleArr = new int[3][3];
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        nextPuzzleArr[j][k] = current.arr[j][k];
                    }
                }

                // swap
                int temp = nextPuzzleArr[nx][ny];
                nextPuzzleArr[nx][ny] = nextPuzzleArr[current.target[0]][current.target[1]];
                nextPuzzleArr[current.target[0]][current.target[1]] = temp;
                Puzzle next = new Puzzle(nextPuzzleArr, new int[]{nx, ny}, current.cost+1);
                if (!visited.contains(next)) {
                    queue.add(next);
                    visited.add(next);
                }
            }
        }

        System.out.println(answer);

    }

    static class Puzzle {
        int[][] arr;
        int[] target;
        int cost;

        public Puzzle(int[][] arr, int[] target, int cost) {
            this.arr = arr;
            this.target = target;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            Puzzle oPuzzle = (Puzzle) o;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (this.arr[i][j] != oPuzzle.arr[i][j]) return false;
                }
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = 1;
            for (int i = 0; i < arr.length; i++) {
                int rowHash = 1;
                for (int j = 0; j < arr[i].length; j++) {
                    rowHash = 31 * rowHash + arr[i][j];
                }
                result = 31 * result + rowHash;
            }
            return result;
        }
    }

}
