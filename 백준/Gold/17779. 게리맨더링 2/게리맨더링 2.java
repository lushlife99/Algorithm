import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 17779 게리맨더링 2
 *
 */

/**
 * 반례
 * 각 구역 중 하나는 있어야 한다.
 */

public class Main {

    private static int N;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int d1 = 1; d1 < N; d1++) {
            for (int d2 = 1; d2 < N; d2++) {
                for (int x = 0; x+d1+d2 < N; x++) {
                    for (int y = d1; y+d2 < N; y++) {
                        boolean[][] visited = paintMiddle(x, y, d1, d2);

                        int sum = 0;

                        // area 5
                        for (int i = 0; i < N; i++) {
                            for (int j = 0; j < N; j++) {
                                if (!visited[i][j]) continue;
                                sum+=arr[i][j];
                            }
                        }

                        int max = sum;
                        int min = sum;

                        // area 1
                        sum = getSum(0, 0, x + d1 - 1, y, visited);
                        max = Math.max(max, sum);
                        min = Math.min(min, sum);

                        // area 2
                        sum = getSum(0, y+1, x + d2, N-1, visited);
                        max = Math.max(max, sum);
                        min = Math.min(min, sum);

                        // area 3
                        sum = getSum(x+d1, 0, N-1, y-d1+d2-1, visited);
                        max = Math.max(max, sum);
                        min = Math.min(min, sum);

                        // area 4
                        sum = getSum(x+d2+1, y-d1+d2, N-1, N-1, visited);
                        max = Math.max(max, sum);
                        min = Math.min(min, sum);

                        answer = Math.min(answer, max-min);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static int getSum(int sx, int sy, int ex, int ey, boolean[][] visited) {
        int sum = 0;
        for (int i = sx; i <= ex; i++) {
            for (int j = sy; j <= ey; j++) {
                if (!visited[i][j]) sum += arr[i][j];
            }
        }

        return sum;
    }

    private static boolean[][] paintMiddle(int x, int y, int d1, int d2) {
        List<int[]> borders = new ArrayList<>();
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i <= d1; i++) {
            borders.add(new int[]{x+i, y-i});
            borders.add(new int[]{x+d1+d2-i, y-d1+d2+i});
        }

        for (int i = 0; i < d2; i++) {
            borders.add(new int[]{x+i, y+i});
            borders.add(new int[]{x+d1+d2-i, y-d1+d2-i});
        }

        Collections.sort(borders, (b1, b2) -> {
            if (b1[0] != b2[0]) return b1[0] - b2[0];
            return b1[1] - b2[1];
        });

        for (int i = 0; i < borders.size(); i+=2) {
            int[] c1 = borders.get(i);
            int[] c2 = borders.get(i+1);

            for (int j = c1[1]; j <= c2[1]; j++) {
                visited[c1[0]][j] = true;
            }
        }

        return visited;
    }
}