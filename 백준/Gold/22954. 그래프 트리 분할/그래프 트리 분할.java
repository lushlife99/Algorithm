import java.io.*;
import java.util.*;

/**
 * boj 22954
 */

public class Main {

    static int N, M;
    static List<Edge>[] graph;
    static boolean[] visited;
    static int[][] edgeInput;

    static class Edge {
        int idx, to;
        Edge(int idx, int to) {
            this.idx = idx;
            this.to = to;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (N <= 2 || M <= N - 3) {
            System.out.println(-1);
            return;
        }

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        edgeInput = new int[M + 1][3]; // idx, a, b

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(i, b));
            graph[b].add(new Edge(i, a));
            edgeInput[i][0] = i;
            edgeInput[i][1] = a;
            edgeInput[i][2] = b;
        }

        visited = new boolean[N + 1];
        List<Result> components = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                components.add(dfs(i));
            }
        }

        if (components.size() > 2) {
            System.out.println(-1);
            return;
        }

        if (components.size() == 2) {
            Result comp1 = components.get(0);
            Result comp2 = components.get(1);
            if (comp1.nodes.size() == comp2.nodes.size()) {
                System.out.println(-1);
                return;
            }

            System.out.println(comp1.nodes.size() + " " + comp2.nodes.size());
            printList(comp1.nodes);
            printList(comp1.edges);
            printList(comp2.nodes);
            printList(comp2.edges);
            return;
        }

        Result comp = components.get(0);

        List<Integer> curEdges = new ArrayList<>();
        for (int idx : comp.edges) {
            int a = edgeInput[idx][1];
            int b = edgeInput[idx][2];
            if (a == comp.lastNode || b == comp.lastNode) continue;
            curEdges.add(idx);
        }

        System.out.println((comp.nodes.size() - 1) + " 1");
        for (int v : comp.nodes) {
            if (v != comp.lastNode) System.out.print(v + " ");
        }
        System.out.println();
        printList(curEdges);
        System.out.println(comp.lastNode);
    }

    static class Result {
        List<Integer> nodes;
        List<Integer> edges;
        int lastNode;

        Result(List<Integer> nodes, List<Integer> edges, int lastNode) {
            this.nodes = nodes;
            this.edges = edges;
            this.lastNode = lastNode;
        }
    }

    static Result dfs(int start) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> nodes = new ArrayList<>();
        List<Integer> edges = new ArrayList<>();

        stack.push(start);
        visited[start] = true;
        nodes.add(start);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            for (Edge e : graph[node]) {
                if (!visited[e.to]) {
                    visited[e.to] = true;
                    nodes.add(e.to);
                    edges.add(e.idx);
                    stack.push(e.to);
                }
            }
        }

        return new Result(nodes, edges, nodes.get(nodes.size() - 1));
    }

    static void printList(List<Integer> list) {
        for (int v : list) System.out.print(v + " ");
        System.out.println();
    }
}
