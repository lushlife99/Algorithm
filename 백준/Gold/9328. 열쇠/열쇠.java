import java.io.*;
import java.util.*;

/**
 * BOJ 9328 열쇠
 *
 * 메모리 초과때문에 답지 봄
 */


public class Main {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()) + 2;
            M = Integer.parseInt(st.nextToken()) + 2;

            char[][] map = new char[N][M];
            boolean[][] visited = new boolean[N][M];

            // 패딩된 맵 초기화
            for (int i = 0; i < N; i++) Arrays.fill(map[i], '.');
            for (int i = 1; i < N - 1; i++) {
                String line = br.readLine();
                for (int j = 1; j < M - 1; j++) {
                    map[i][j] = line.charAt(j - 1);
                }
            }

            // 초기 키
            Set<Character> keySet = new HashSet<>();
            String keyLine = br.readLine();
            if (!keyLine.equals("0")) {
                for (char c : keyLine.toCharArray()) {
                    keySet.add(Character.toUpperCase(c));
                }
            }

            Queue<int[]> queue = new LinkedList<>();
            Map<Character, List<int[]>> doorWait = new HashMap<>();
            int docCount = 0;

            queue.offer(new int[]{0, 0});
            visited[0][0] = true;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];

                for (int dir = 0; dir < 4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    if (visited[nx][ny]) continue;
                    char ch = map[nx][ny];
                    if (ch == '*') continue;

                    visited[nx][ny] = true;

                    // 문서
                    if (ch == '$') {
                        docCount++;
                        queue.offer(new int[]{nx, ny});
                    }
                    // 열쇠
                    else if (ch >= 'a' && ch <= 'z') {
                        char door = Character.toUpperCase(ch);
                        if (!keySet.contains(door)) {
                            keySet.add(door);
                            if (doorWait.containsKey(door)) {
                                for (int[] pos : doorWait.get(door)) {
                                    queue.offer(pos);
                                }
                            }
                        }
                        queue.offer(new int[]{nx, ny});
                    }
                    // 문
                    else if (ch >= 'A' && ch <= 'Z') {
                        if (keySet.contains(ch)) {
                            queue.offer(new int[]{nx, ny});
                        } else {
                            doorWait.computeIfAbsent(ch, k -> new ArrayList<>()).add(new int[]{nx, ny});
                        }
                    }
                    // 빈 공간
                    else {
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }

            System.out.println(docCount);
        }
    }
}
