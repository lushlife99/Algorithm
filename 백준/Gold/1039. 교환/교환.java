import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 1039 교환
 * bfs
 */


public class Main {

    private static boolean[][] visited;
    private static int K;
    private static int N_length;
    private static int maxResult = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String N_str = st.nextToken();
        K = Integer.parseInt(st.nextToken());
        N_length = N_str.length();

        visited = new boolean[K + 1][1000001];

        if (N_length == 1 || (N_length == 2 && N_str.charAt(1) == '0')) {
            System.out.println(-1);
            return;
        }

        bfs(N_str);

        System.out.println(maxResult);
    }

    private static void bfs(String startNum) {
        Queue<State> queue = new ArrayDeque<>();
        queue.add(new State(startNum, 0));

        int startInt = Integer.parseInt(startNum);
        if (startInt < 1000001) {
            visited[0][startInt] = true;
        }


        while (!queue.isEmpty()) {
            State current = queue.poll();
            String currentNum = current.num;
            int count = current.count;

            if (count == K) {
                maxResult = Math.max(maxResult, Integer.parseInt(currentNum));
                continue;
            }

            for (int i = 0; i < N_length; i++) {
                for (int j = i + 1; j < N_length; j++) {

                    String nextNum = swap(currentNum, i, j);

                    if (nextNum.charAt(0) == '0') {
                        continue;
                    }

                    int nextInt = Integer.parseInt(nextNum);

                    if (nextInt < 1000001) {
                        if (!visited[count + 1][nextInt]) {
                            visited[count + 1][nextInt] = true;
                            queue.add(new State(nextNum, count + 1));
                        }
                    }
                }
            }
        }
    }

    private static String swap(String s, int i, int j) {
        char[] arr = s.toCharArray();
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return new String(arr);
    }

    static class State {
        String num;
        int count;

        public State(String num, int count) {
            this.num = num;
            this.count = count;
        }
    }
}