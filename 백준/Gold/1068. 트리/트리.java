import java.io.*;
import java.util.*;

/**
 * boj 1068 트리
 *
 */

public class Main {

    static int N;
    static int[] parent;
    static int root;
    static boolean[] delete;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        parent = new int[N];
        delete = new boolean[N];
        for (int i = 0; i < N; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
            if (parent[i] == -1) root = i;
        }

        delete[Integer.parseInt(br.readLine())] = true;
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < N; i++) {
                if (i == root) continue;
                if (delete[i]) continue;
                if (delete[parent[i]]) {
                    delete[i] = true;
                    changed = true;
                }
            }
        }

        int[] inDegree = new int[N];
        for (int i = 0; i < N; i++) {
            if (i == root) continue;
            if (delete[i]) continue;
            inDegree[parent[i]]++;
        }
        int answer = 0;
        for (int i =0 ; i < N; i++) {
            if (delete[i]) continue;
            if (inDegree[i] == 0) answer++;
        }
        System.out.println(answer);

    }
}
