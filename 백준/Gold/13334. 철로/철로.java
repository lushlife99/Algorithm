import java.io.*;
import java.util.*;

/**
 * boj 13334 철로
 */


public class Main {

    static class Pair implements Comparable<Pair> {
        int s, e;

        public Pair(int s, int e) {
            this.s = s; this.e = e;
        }

        @Override
        public int compareTo(Pair o) {
            if (e != o.e) return e-o.e;
            return s - o.s;
        }
    }

    private static int n,d;
    private static List<Pair> pairs = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            Pair p = new Pair(Math.min(n1,n2), Math.max(n1,n2));
            pairs.add(p);
        }
        d = Integer.parseInt(br.readLine());
        Collections.sort(pairs);
        Queue<Pair> queue = new PriorityQueue<>((p1, p2) -> {
            return p1.s - p2.s;
        });
        int answer = 0;
        for (Pair p : pairs) {
            if (p.e-p.s > d) continue;

            int end = p.e;
            int start = end-d;

            queue.add(p);

            while(!queue.isEmpty() && queue.peek().s < start) {
                queue.poll();
            }

            answer = Math.max(answer, queue.size());
        }

        System.out.println(answer);
    }

}