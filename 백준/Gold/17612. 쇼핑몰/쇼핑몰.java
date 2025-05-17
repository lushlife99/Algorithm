import java.io.*;
import java.util.*;

public class Main {
    static class Person {
        int id, w;
        public Person(int id, int w) { this.id = id; this.w = w; }
    }

    static class ExitLog {
        Person person;
        int end, exchangeIdx;
        public ExitLog(Person person, int end, int exchangeIdx) {
            this.person = person;
            this.end = end;
            this.exchangeIdx = exchangeIdx;
        }
    }

    static class Exchange {
        int id, total;
        public Exchange(int id, int total) {
            this.id = id;
            this.total = total;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Exchange> exPq = new PriorityQueue<>((a, b) -> {
            if (a.total != b.total) return Integer.compare(a.total, b.total);
            return Integer.compare(a.id, b.id);
        });

        for (int i = 0; i < K; i++) exPq.add(new Exchange(i, 0));

        PriorityQueue<ExitLog> pq = new PriorityQueue<>((a, b) -> {
            if (a.end != b.end) return Integer.compare(a.end, b.end);
            return Integer.compare(b.exchangeIdx, a.exchangeIdx); // 큰 번호 우선
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            Exchange exchange = exPq.poll();
            exchange.total += w;
            exPq.add(exchange);

            Person p = new Person(id, w);
            pq.add(new ExitLog(p, exchange.total, exchange.id));
        }

        long answer = 0L;
        for (int i = 0; !pq.isEmpty(); i++) {
            ExitLog el = pq.poll();
            answer += (long)(i + 1) * el.person.id;
        }

        System.out.println(answer);
    }
}
