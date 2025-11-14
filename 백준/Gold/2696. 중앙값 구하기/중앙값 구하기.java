import java.io.*;
import java.util.*;


/**
 * boj 2696 중앙값 구하기
 * 우선순위 큐 두개
 *
 * maxH, minH
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            sb.append((N + 1) / 2).append("\n");

            PriorityQueue<Integer> maxH = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> minH = new PriorityQueue<>();

            StringTokenizer st = new StringTokenizer(br.readLine());

            int cnt = 0;

            for (int i = 1; i <= N; i++) {

                if (!st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }

                int num = Integer.parseInt(st.nextToken());

                if (maxH.size() == minH.size()) {
                    maxH.add(num);
                } else {
                    minH.add(num);
                }

                if (!minH.isEmpty() && maxH.peek() > minH.peek()) {
                    int a = maxH.poll();
                    int b = minH.poll();
                    maxH.add(b);
                    minH.add(a);
                }

                if (i % 2 == 1) {
                    sb.append(maxH.peek()).append(" ");
                    cnt++;

                    if (cnt % 10 == 0) {
                        sb.append("\n");
                    }
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
