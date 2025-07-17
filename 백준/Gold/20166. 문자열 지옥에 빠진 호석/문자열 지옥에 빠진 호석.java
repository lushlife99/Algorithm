import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 10^2 * 10 * 5 *
 * BOJ 20166
 * i, j 에서 시작한 k 길이의 문자열 dp
 * dp[i][j][k] = dp[prev][prev][k-1] + arr[i][j]
 */
public class Main {

    static int N;
    static int M;
    static int K;
    static char[][] arr;
    static Map<Key, Map<String, Integer>> dp = new HashMap<>();
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};

    static class Key {
        int x, y, len;

        public Key(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            Key current = (Key) o;
            return x == current.x && y == current.y && len == current.len;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, len);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        K = Integer.parseInt(split[2]);
        arr = new char[N][];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            arr[i] = s.toCharArray();
        }

        for (int k = 1; k <= 5; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    setDp(i, j, k);
                }
            }
        }

        for (int i = 0; i < K; i++) {
            String target = br.readLine();
            int len = target.length();
            int count = 0;

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    Key key = new Key(x, y, len);
                    Map<String, Integer> stringCntMap = dp.getOrDefault(key, new HashMap<>());
                    count += stringCntMap.getOrDefault(target, 0);
                }
            }
            System.out.println(count);
        }
    }

    private static void setDp(int x, int y, int len) {

        Key current = new Key(x, y, len);
        dp.computeIfAbsent(current, k -> new HashMap<>());
        if (len == 1) {
            dp.get(current).put(String.valueOf(arr[x][y]), 1);
            return;
        }


        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx == -1) nx = N - 1;
            else if (nx == N) nx = 0;
            if (ny == -1) ny = M - 1;
            else if (ny == M) ny = 0;
            Key key = new Key(nx, ny, len - 1);
            Map<String, Integer> stringCntMap = dp.get(key);
            for (String s : stringCntMap.keySet()) {
                String ns = s + arr[x][y];
                Map<String, Integer> currentStringCntMap = dp.get(current);
                currentStringCntMap.put(ns, currentStringCntMap.getOrDefault(ns, 0) + stringCntMap.get(s));
            }
        }

    }


}