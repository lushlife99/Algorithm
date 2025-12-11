import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 10800 컬러볼
 */

/**
 * 반례
 * 같은 크기와 같은 색
 */

public class Main {

    private static int N;
    private static List<int[]> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            arr.add(new int[]{i, color, value});
        }

        Collections.sort(arr, (a,b) -> a[2]-b[2]);

        int[] groupSum = new int[N+1];
        int[] answer = new int[N];
        int total = 0;
        int idx = 0;

        for (int i = 0; i < N; i++) {
            while (arr.get(idx)[2] < arr.get(i)[2]) {
                total += arr.get(idx)[2];
                groupSum[arr.get(idx)[1]] += arr.get(idx)[2];
                idx++;
            }

            answer[arr.get(i)[0]] = total - groupSum[arr.get(i)[1]];
        }

        StringBuilder sb = new StringBuilder();
        for (int i : answer) sb.append(i).append("\n");
        
        System.out.print(sb);
    }
}