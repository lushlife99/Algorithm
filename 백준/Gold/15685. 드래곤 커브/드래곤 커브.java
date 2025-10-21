import java.io.*;
import java.util.*;

/**
 * boj 15685 드래곤 커브
 * 0 -> 1
 * 1 -> 2
 * 2 -> 3
 * 3 -> 0
 */

public class Main {

    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, -1, 0, 1};

    private static int N;
    private static boolean[][] visited;

    static class DragonCurve {
        int x, y, dir, age;

        public DragonCurve(int x, int y, int dir, int age) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.age = age;

            build();
        }

        private void build() {
            int currentAge = 0;

            int cx = x + dx[dir];
            int cy = y + dy[dir];
            List<Integer> directions = new ArrayList<>(List.of(dir));
            visited[x][y] = true;
            visited[cx][cy] = true;
            while (currentAge < age) {

                for (int i = directions.size()-1; i >= 0; i--) {
                    int nextDir = (directions.get(i) + 1) % dx.length;
                    directions.add(nextDir);

                    cx = cx + dx[nextDir];
                    cy = cy + dy[nextDir];
                    visited[cx][cy] = true;
                }

                currentAge++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[101][101];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            DragonCurve dc = new DragonCurve(x, y, d, g);
        }
        int answer = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

}

