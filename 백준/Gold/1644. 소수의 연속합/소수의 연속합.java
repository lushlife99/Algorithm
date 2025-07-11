import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 소수, 투포인터
 * N까지의 소수 구하기
 * 투포인터
 */

public class Main {

    static int N;
    static List<Integer> arr = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        for (int i = 2; i <= N; i++) {
            if (!visited[i]) {
                arr.add(i);
                visited[i] = true;
                for (int j = 2; j * i <= N; j++) {
                    visited[j*i] = true;
                }
            }
        }

        int left = -1, right = -1;
        int sum = 0;
        int answer = 0;
        while (right < arr.size()-1) {
            sum += arr.get(++right);
            while (sum > N) {
                sum -= arr.get(++left);
            }

            if (sum == N) {
                answer++;
            }
        }
        System.out.println(answer);
    }

}
