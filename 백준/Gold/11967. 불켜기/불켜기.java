import java.io.*;
import java.util.*;

/**
 * boj 11967 불켜기
 *
 */

//반례
//5 5
//1 1 1 2
//1 1 1 3
//1 2 1 4
//1 4 2 1
//2 1 5 5



public class Main {

    static int N, M;
    static Map<Integer, List<int[]>> switchMap = new HashMap<>();
    static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cx = Integer.parseInt(st.nextToken());
            int cy = Integer.parseInt(st.nextToken());
            int nx = Integer.parseInt(st.nextToken());
            int ny = Integer.parseInt(st.nextToken());
            int key = cx*101+cy;
            switchMap.putIfAbsent(key, new ArrayList<>());
            switchMap.get(key).add(new int[]{nx,ny});
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][N+1];
        Set<Integer> answer = new HashSet<>();

        queue.add(new int[]{1,1});
        visited[1][1] = true;
        answer.add(102);

        while(!queue.isEmpty()) {
            int[] c = queue.poll();
            int key = c[0]*101+c[1];

            if (switchMap.containsKey(key)) {
                for (int[] n : switchMap.get(key)) {
                    if (answer.add(n[0]*101+n[1])) {
                        for (int i = 0; i < directions.length; i++) {
                            int nx = n[0] + directions[i][0];
                            int ny = n[1] + directions[i][1];

                            if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
                            if (visited[nx][ny]) {
                                queue.add(new int[]{nx,ny});
                                visited[nx][ny] = true;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < directions.length; i++) {
                int nx = c[0] + directions[i][0];
                int ny = c[1] + directions[i][1];
                if (nx < 1 || ny < 1 || nx > N || ny > N) continue;

                int nextKey = nx*101+ny;
                if (answer.contains(nextKey) && !visited[nx][ny]) {
                    queue.add(new int[]{nx,ny});
                    visited[nx][ny] = true;
                }
            }
        }

        System.out.println(answer.size());
    }
}