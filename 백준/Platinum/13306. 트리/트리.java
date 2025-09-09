import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * boj 17398 통신망 분할
 * 분리 집합
 */


public class Main {

    static int N, Q;
    static int[] parent, edge;
    static boolean[] remove;

    public abstract static class Command {
        String query;

        public Command(String query) {
            this.query = query;
        }

        public abstract String run();
    }

    public static class Delete extends Command {

        public Delete(String query) {
            super(query);
        }

        // delete 는 add의 역연산
        @Override
        public String run() {
            assert(query.split(" ").length == 2);

            int u = Integer.parseInt(query.split(" ")[1]);
            union(u, edge[u]);

            return null;
        }
    }

    public static class Read extends Command {

        public Read(String query) {
            super(query);
        }

        @Override
        public String run() {
            assert(query.split(" ").length == 3);

            String[] split = query.split(" ");
            int ru = find(Integer.parseInt(split[1]));
            int rv = find(Integer.parseInt(split[2]));

            if (ru == rv) return "YES";
            return "NO";
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        edge = new int[N+1];
        parent = IntStream.rangeClosed(0, N).toArray();
        remove = new boolean[N+1];

        for (int u = 2; u <= N; u++) {
            int v = Integer.parseInt(br.readLine());

            edge[u] = v;
        }

        List<Command> commands = new ArrayList<>();

        for (int i = 0; i < N-1+Q; i++) {
            String q = br.readLine();
            String[] tokens = q.split(" ");
            if (tokens.length == 2) {
                remove[Integer.parseInt(tokens[1])] = true;
                commands.add(new Delete(q));
            } else {
                commands.add(new Read(q));
            }
        }

        for (int i = 2; i <= N; i++) {
            if (!remove[i]) {
                union(i, edge[i]);
            }
        }

        Stack<String> ans = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = commands.size() - 1; i >= 0; i--) {
            Command c = commands.get(i);

            String res = c.run();
            if (res != null) {
                ans.add(res);
            }
        }

        while (!ans.isEmpty()) {
            sb.append(ans.pop() + "\n");
        }

        System.out.println(sb.toString());
    }

    public static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

}
