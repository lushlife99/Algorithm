import java.io.*;

/**
 * BOJ 10775
 * 그리디? 반례 찾기
 * 100 -> 100번에 도킹
 * 100 -> 100번 있으니까 99번 도킹
 * 2중 반복 시간초과
 */
public class Main {

    static int G;
    static int P;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        parent = new int[G + 1];
        for (int i = 0; i <= G; i++) {
            parent[i] = i;
        }

        int result = 0;

        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine());
            int dockingGate = find(g);

            if (dockingGate == 0) break;
            union(dockingGate, dockingGate - 1);
            result++;
        }
        System.out.println(result);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[a] = b;
    }

    static int find(int a) {
        while (a != parent[a]) {
            parent[a] = parent[parent[a]];
            a = parent[a];
        }
        return a;
    }
}