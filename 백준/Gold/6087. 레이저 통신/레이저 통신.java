import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.util.*;

public class Main {

    private static int W, H;
    private static char[][] arr;

    private static int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new char[H][W];
        int[] s = null;
        int[] e = null;

        for (int i = 0; i < H; i++) {
            arr[i] = br.readLine().toCharArray();

            for (int j = 0; j < W; j++) {
                if (arr[i][j] == 'C') {
                    if (s == null) s = new int[]{i, j};
                    else e = new int[]{i,j};
                }
            }
        }

        int[][][] distance = new int[H][W][4];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(distance[i][j], Integer.MAX_VALUE);
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < directions.length; i++) {
            int x = s[0];
            int y = s[1];

            while (0 <= x && x < H && 0 <= y && y < W) {
                if (arr[x][y] == '*') break;

                distance[x][y][i] = 0;
                queue.add(new int[]{x,y,i});
                x += directions[i][0];
                y += directions[i][1];
            }
        }


        int cnt = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] c = queue.poll();

                if (c[0] == e[0] && c[1] == e[1]) {
                    System.out.println(cnt);
                    return;
                }


                int[] nd = new int[]{(c[2] + 1) % 4, (c[2]+3)%4};

                for (int d : nd) {
                    int x = c[0];
                    int y = c[1];
                    while (0 <= x && x < H && 0 <= y && y < W) {
                        if (arr[x][y] == '*') break;
                        if (distance[x][y][d] <= cnt+1) break;

                        distance[x][y][d] = cnt+1;
                        queue.add(new int[]{x, y, d});

                        x += directions[d][0];
                        y += directions[d][1];
                    }
                }
            }

            cnt++;
        }



    }
}