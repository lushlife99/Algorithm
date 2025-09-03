import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] from;
    static int[] stack, id, groupId;
    static int top, currentId;
    static List<List<Integer>> sccGroup;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        from = new ArrayList[2 * N + 1];
        for (int i = 0; i <= 2 * N; i++) from[i] = new ArrayList<>();

        stack = new int[2 * N + 1];
        id = new int[2 * N + 1];
        groupId = new int[2 * N + 1];
        top = 0;
        currentId = 0;
        sccGroup = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            if (f < 0) f = -f + N;
            if (s < 0) s = -s + N;

            from[neg(f)].add(s);
            from[neg(s)].add(f);
        }

        for (int v = 1; v <= 2 * N; v++) {
            if (id[v] == 0) traverse(v);
        }

        boolean ok = true;
        for (int v = 1; v <= N; v++) {
            if (groupId[v] == groupId[neg(v)]) {
                ok = false;
                break;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        if (!ok) {
            bw.write("0\n");
            bw.flush();
            return;
        }

        bw.write("1\n");
        for (int v = 1; v <= N; v++) {
            bw.write((groupId[v] < groupId[neg(v)] ? "1" : "0") + (v == N ? "\n" : " "));
        }
        bw.flush();
    }

    static int neg(int x) {
        return x > N ? x - N : x + N;
    }

    static int traverse(int node) {
        id[node] = ++currentId;
        stack[top++] = node;

        int lowlink = id[node];
        for (int next : from[node]) {
            if (id[next] == 0) {
                lowlink = Math.min(lowlink, traverse(next));
            } else if (groupId[next] == 0) {
                lowlink = Math.min(lowlink, id[next]);
            }
        }

        if (lowlink == id[node]) {
            sccGroup.add(new ArrayList<>());
            while (true) {
                int now = stack[--top];
                groupId[now] = sccGroup.size();
                sccGroup.get(sccGroup.size() - 1).add(now);
                if (now == node) break;
            }
        }

        return lowlink;
    }
}
