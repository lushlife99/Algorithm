import java.io.*;
import java.util.*;


/**
 * boj 13904 과제
 * 그리디, 정렬, 우선순위 큐
 */

public class Main {

    static class Submit implements Comparable<Submit> {
        int deadLine, score;

        public Submit(int deadLine, int score) {
            this.deadLine = deadLine;
            this.score = score;
        }

        @Override
        public int compareTo(Submit o) {
            return this.deadLine - o.deadLine;
        }
    }

    private static int N;
    private static List<Submit> submits = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            submits.add(new Submit(deadLine, score));
        }

        // 정렬
        Collections.sort(submits);

        // 그리디
        Queue<Submit> queue = new PriorityQueue<>((s1, s2) -> {
            return s1.score - s2.score;
        });

        int day = 0;
        for (Submit s : submits) {
            if (s.deadLine > day) {
                day++;
                queue.add(s);
            } else {
                Submit minScoreSubmit = queue.poll();

                if (s.score >= minScoreSubmit.score) {
                    queue.add(s);
                } else {
                    queue.add(minScoreSubmit);
                }
            }
        }

        int answer = 0;
        for (Submit s : queue) {
            answer += s.score;
        }
        System.out.println(answer);
    }

}
