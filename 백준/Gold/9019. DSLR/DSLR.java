import java.io.*;
import java.util.*;

/**
 * boj 9019 DSLR
 *
 */

public class Main {

    static class Node {
        int val;
        String query;

        Node(int val, String query) {
            this.val = val; this.query = query;
        }
    }

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sb.append(getAnswer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            sb.append("\n");
        }

        System.out.println(sb);

    }

    static String getAnswer(int current, int target) {
        boolean[] visited = new boolean[10_000];
        visited[current] = true;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(current, ""));

        String answer = "";
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            if (n.val == target) {
                answer = n.query;
                break;
            }

            int nd = D(n.val);
            if (!visited[nd]) {
                visited[nd] = true;
                queue.add(new Node(nd, n.query+"D"));
            }

            int ns = S(n.val);
            if (!visited[ns]) {
                visited[ns] = true;
                queue.add(new Node(ns, n.query+"S"));
            }

            int nl = L(n.val);
            if (!visited[nl]) {
                visited[nl] = true;
                queue.add(new Node(nl, n.query+"L"));
            }

            int nr = R(n.val);
            if (!visited[nr]) {
                visited[nr] = true;
                queue.add(new Node(nr, n.query+"R"));
            }
        }

        return answer;
    }

    static int D(int current) {
        current = current*2;
        return current > 9999 ? current % 10000 : current;
    }

    static int S(int current) {
        return current == 0 ? 9999 : current - 1;
    }

    static int L(int current) {
        int val = 0;
        val += current % 1000 * 10;
        val += current / 1000;
        return val;
    }

    static int R(int current) {
        int val = 0;
        val += current % 10 * 1000;
        val += current / 10;
        return val;
    }
}