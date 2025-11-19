import java.io.*;
import java.util.*;


/**
 * boj 17612 쇼핑몰
 */

/**
 * 반례
 * 작업 우선순위 큐에서 가장 빨리 끝나는 노드가 여러개일 경우
 */

public class Main {

    static class Customer {
        int id, w;
        int end, nodeId;

        public Customer(int id, int w) {
            this.id = id; this.w = w;
        }

        @Override
        public String toString() {
            return "id : " + this.id + ", end : " + this.end + ", nodeId : " + nodeId;
        }
    }

    static class Node {
        int id;
        int end;

        public Node(int id, int end) {
            this.id = id;
            this.end = end;
        }
    }

    private static int N, K;
    private static List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            customers.add(new Customer(id, w));
        }

        Queue<Node> queue = new PriorityQueue<>((n1, n2) -> {
            if (n1.end != n2.end) return n1.end - n2.end;
            return n1.id - n2.id;
        });

        for (int i = 1; i <= K; i++) {
            queue.add(new Node(i, 0));
        }

        for (int i = 0; i < N; i++) {
            Customer customer = customers.get(i);
            Node node = queue.poll();
            node.end += customer.w;
            queue.offer(node);

            customer.nodeId = node.id;
            customer.end = node.end;
        }

        long answer = 0;
        Collections.sort(customers, (c1, c2) -> {
            if (c1.end != c2.end) return c1.end - c2.end;

            return c2.nodeId - c1.nodeId;
        });

        for (long i = 1; i <= customers.size(); i++) {
            Customer c = customers.get((int)i-1);
            answer += (i * c.id);
        }

        System.out.println(answer);
    }


}
