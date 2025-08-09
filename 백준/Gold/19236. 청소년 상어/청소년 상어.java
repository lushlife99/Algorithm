import java.io.*;
import java.util.*;

/**
 * BOJ 19236 청소년 상어
 * 백트래킹
 */


public class Main {
    static int[][] fishIdArr = new int[4][4];
    static List<Fish> fishes = new ArrayList<>();
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    static class Fish implements Comparable<Fish> {
        int id;
        int direction;
        int x;
        int y;

        public Fish(int id, int direction, int x, int y) {
            this.id = id;
            this.direction = direction;
            this.x = x;
            this.y = y;
        }

        public Fish(Fish f) {
            this.id = f.id;
            this.direction = f.direction;
            this.x = f.x;
            this.y = f.y;
        }

        @Override
        public int compareTo(Fish o) {
            return this.id - o.id;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int id = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                fishIdArr[i][j] = id;
                fishes.add(new Fish(id, direction-1, i, j));
            }
        }

        Fish first = fishes.get(0);
        Collections.sort(fishes);
        fishIdArr[0][0] = 0;
        System.out.println(dfs(0, 0, first.direction, new HashSet<>(Set.of(first.id))));

    }

    private static int dfs(int x, int y, int d, Set<Integer> ateFishIds) {
        int res = -1;


        List<Fish> prevFish = new ArrayList<>();
        for (int i = 0 ; i < fishes.size(); i++) {
            prevFish.add(new Fish(fishes.get(i)));
        }

        int[][] prevIds = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                prevIds[i][j] = fishIdArr[i][j];
            }
        }


        for (Fish fish : fishes) {
            if (ateFishIds.contains(fish.id)) continue;

            for (int i = 0; i < dx.length; i++) {
                int nd = (fish.direction + i) % dx.length;
                int nx = fish.x + dx[nd];
                int ny = fish.y + dy[nd];

                if (nx < 0 || ny < 0 || nx == 4 || ny == 4) continue;
                if (nx == x && ny == y) continue;

                if (fishIdArr[nx][ny] == 0) {
                    fishIdArr[fish.x][fish.y] = 0;
                    fishIdArr[nx][ny] = fish.id;
                    fish.x = nx;
                    fish.y = ny;
                }
                else {
                    int otherId = fishIdArr[nx][ny];
                    Fish other = fishes.get(otherId - 1);

                    int tempX = other.x, tempY = other.y;
                    other.x = fish.x;
                    other.y = fish.y;
                    fish.x = tempX;
                    fish.y = tempY;

                    fishIdArr[other.x][other.y] = other.id;
                    fishIdArr[fish.x][fish.y] = fish.id;
                }

                fish.direction = nd;
                break;
            }
        }

        // 상어 이동
        for (int i = 1; i <= 3; i++) {
            int nx = x + dx[d] * i;
            int ny = y + dy[d] * i;

            if (nx < 0 || ny < 0 || nx == 4 || ny == 4) break;
            int fishId = fishIdArr[nx][ny];
            if (fishId != 0) {
                ateFishIds.add(fishId);
                fishIdArr[nx][ny] = 0;
                res = Math.max(dfs(nx, ny, fishes.get(fishId-1).direction, ateFishIds), res);
                fishIdArr[nx][ny] = fishId;
                ateFishIds.remove(fishId);
            }
        }

        for (int i = 0; i < 4; i++) {
            fishIdArr[i] = Arrays.copyOf(prevIds[i], 4);
        }
        fishes.clear();
        for (Fish f : prevFish) fishes.add(new Fish(f));

        List<Integer> ateFishIdList = new ArrayList<>(ateFishIds);
        int sum = 0;
        for (int id : ateFishIdList) {
            sum += id;
        }
        res = Math.max(sum, res);

        return res;
    }

}