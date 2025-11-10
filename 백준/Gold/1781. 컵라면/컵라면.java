import java.io.*;
import java.util.*;


/**
 * boj 1781 컵라면
 * 그리디
 * NlogN
 */


public class Main {

    static class Quiz implements Comparable<Quiz> {
        int deadLine, score;

        public Quiz(int deadLine, int score) {
            this.deadLine = deadLine; this.score = score;
        }

        @Override
        public int compareTo(Quiz o) {
            return Integer.compare(deadLine, o.deadLine);
        }
    }

    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        List<Quiz> quizzes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            quizzes.add(new Quiz(deadLine, score));
        }

        Collections.sort(quizzes);
        int submitCnt = 0;
        Queue<Quiz> queue = new PriorityQueue<>((q1, q2) -> {
            return Integer.compare(q1.score, q2.score);
        });

        for (Quiz q : quizzes) {

            if (q.deadLine > submitCnt) {
                submitCnt++;
                queue.add(q);
            } else {
                Quiz minScoreQuiz = queue.poll();

                if (q.score >= minScoreQuiz.score) {
                    queue.add(q);
                } else {
                    queue.add(minScoreQuiz);
                }
            }
        }

        int answer = 0;

        for (Quiz q : queue) {
            answer += q.score;
        }

        System.out.println(answer);
    }
}
