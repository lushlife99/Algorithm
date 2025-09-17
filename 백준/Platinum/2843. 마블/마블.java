import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 2843 마블
 * 분리 집합
 */


public class Main {
    static class Query {
        int type;
        int x;
        public Query(int t, int x) { type = t; this.x = x; }
    }
    static int N, Q;
    static int[] to;
    static int[] parent;
    static boolean[] inCycle;
    static Query[] queries;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        to = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            to[i] = Integer.parseInt(st.nextToken());
        }

        Q = Integer.parseInt(br.readLine());
        queries = new Query[Q];

        boolean[] removed = new boolean[N+1];

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            queries[i] = new Query(type, x);
            if (type == 2) {
                removed[x] = true;
            }
        }

        parent = new int[N+1];
        inCycle = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = -1;
        }

        for (int i = 1; i <= N; i++) {
            if (!removed[i] && to[i] != 0) {
                int a = i;
                int b = to[i];
                union(a, b);
            }
        }

        String[] ans = new String[Q];

        for (int i = Q-1; i >= 0; i--) {
            Query q = queries[i];
            if (q.type == 2) {
                if (to[q.x] != 0) {
                    union(q.x, to[q.x]);
                }
            } else {
                int x = q.x;
                int fx = find(x);
                if (inCycle[fx]) {
                    ans[i] = "CIKLUS";
                } else {
                    ans[i] = String.valueOf(fx);
                }
            }
        }

        for (int i = 0; i < Q; i++) {
            if (queries[i].type == 1) {
                bw.write(ans[i]);
                bw.newLine();
            }
        }
        bw.flush();
    }

    static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) {
            inCycle[ra] = true;
        } else {
            parent[ra] = rb;
            inCycle[rb] = inCycle[rb] || inCycle[ra];
        }
    }
}
