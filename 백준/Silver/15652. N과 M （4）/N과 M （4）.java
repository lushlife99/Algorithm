import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = bf.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);
        dfs(N, M, 0, 1, new ArrayList<>());

    }

    public static void dfs(int N, int M, int cnt, int start, List<Integer> arr) {
        if (cnt == M) {
            for (Integer i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i <= N; i++) {
            arr.add(i);
            dfs(N, M, cnt+1, i, arr);
            arr.remove(arr.size()-1);
        }
    }
}
