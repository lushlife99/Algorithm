import java.io.*;
import java.util.*;

public class Main {

    static class Diamond implements Comparable<Diamond> {
        int weight, value;
        public Diamond(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Diamond o) {
            return this.weight - o.weight;  // 무게 오름차순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 보석 수
        int K = Integer.parseInt(st.nextToken()); // 가방 수

        Diamond[] diamonds = new Diamond[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            diamonds[i] = new Diamond(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(diamonds);
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long total = 0;
        int j = 0;

        for (int i = 0; i < K; i++) {
            int capacity = bags[i];

            while (j < N && diamonds[j].weight <= capacity) {
                pq.offer(diamonds[j].value);
                j++;
            }

            if (!pq.isEmpty()) {
                total += pq.poll();
            }
        }

        System.out.println(total);
    }
}
