import java.io.*;
import java.util.*;

/**
 * boj 16235 나무 재테크
 * 시뮬레이션, 자료구조
 * - 정렬 되어 있어야 함,
 */


public class Main {

    static Map<Integer, Deque<Integer>> treeMap = new HashMap<>();

    static int N, M, K;
    static int[][] A;
    static int[][] arr;

    static int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
    static int[] dy = {1, 0, -1, 1, -1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // nxn
        M = Integer.parseInt(st.nextToken()); // 나무 개수
        K = Integer.parseInt(st.nextToken()); // 년도
        A = new int[N][N];
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int key = getKey(i, j);
                treeMap.put(key, new ArrayDeque<>());
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());

            treeMap.get(getKey(r, c)).addFirst(age);
        }

        for (int i = 0; i < K; i++) {
            simulate();
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += treeMap.get(getKey(i, j)).size();
            }
        }

        System.out.println(answer);
    }

    private static int getKey(int x, int y) {
        return x * N + y;
    }

    private static void simulate() {
        List<int[]> appendedTree = new ArrayList<>();

        // 봄, 여름
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Deque<Integer> trees = treeMap.get(getKey(i, j));
                Deque<Integer> aliveTrees = new ArrayDeque<>();
                int append = 0;

                while (!trees.isEmpty()) {
                    int age = trees.removeFirst();
                    if (age <= arr[i][j]) {
                        arr[i][j] -= age;
                        aliveTrees.addLast(age+1);

                        if ((age+1) % 5  == 0) {
                            appendedTree.add(new int[]{i, j});
                        }
                    } else {
                        append += age/2;
                    }
                }
                treeMap.put(getKey(i, j), aliveTrees);
                arr[i][j] += append;
            }
        }

        // 가을
        for(int[] append : appendedTree) {
            for (int i = 0; i < dx.length; i++) {
                int nx = append[0] + dx[i];
                int ny = append[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                treeMap.get(getKey(nx, ny)).addFirst(1);
            }
        }

        // 겨울
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] += A[i][j];
            }
        }
    }

}

