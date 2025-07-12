import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static Map<Integer, List<Integer>> map = new HashMap<>();;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        visited = new boolean[N+1];

        for(int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            split = br.readLine().split(" ");
            for (int j = 1; j < split.length-1; j++) {
                int from = Integer.parseInt(split[j]);
                int to = Integer.parseInt(split[j+1]);

                map.get(to).add(from);
            }
        }

        int cnt = 0;
        ArrayList<Integer> answer = new ArrayList<>();
        
        while(cnt != N) {
            int temp = cnt;
            for (int i = 1; i <= N; i++) {
                if (visited[i]) continue;

                List<Integer> parents = map.get(i);
                boolean signal = true;
                for (Integer parent : parents) {
                    if (!visited[parent]) {
                        signal = false;
                        break;
                    }
                }

                if (signal) {
                    visited[i] = true;
                    answer.add(i);
                    cnt++;
                }

            }

            if (temp == cnt) {
                System.out.println(0);
                break;
            }
        }

        for (Integer i : answer) {
            System.out.println(i);
        }


    }


}
