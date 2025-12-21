import java.io.*;
import java.util.*;

/**
 * boj 1655 가운데를 말해요
 * 우선순위 큐
 */


public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> minHeap = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                maxHeap.add(val);
            } else {
                minHeap.add(val);
            }

            while (!minHeap.isEmpty() && !maxHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                int max = maxHeap.poll();
                int min = minHeap.poll();

                maxHeap.add(min);
                minHeap.add(max);
            }

            sb.append(maxHeap.peek()).append("\n");
        }

        System.out.print(sb);
    }
}