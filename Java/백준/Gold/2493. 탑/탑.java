import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * boj 2493 탑
 */

public class Main {

    static int N;
    static int[] height;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        height = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> minHeap = new PriorityQueue<>((a, b) -> {
            return height[a] - height[b];
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            while (!minHeap.isEmpty() && height[minHeap.peek()] < height[i]) {
                minHeap.poll();
            }

            int answer = 0;
            if (!minHeap.isEmpty()) answer = minHeap.peek();

            sb.append(answer).append(" ");
            minHeap.add(answer);
            minHeap.add(i);
        }

        System.out.println(sb);
    }
}