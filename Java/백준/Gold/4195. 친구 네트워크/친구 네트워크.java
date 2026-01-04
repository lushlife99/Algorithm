import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 4195 친구 네트워크
 * 유니온 파인드
 */


public class Main {

    static int T, F, id;
    static Map<String, Integer> nameIdxMap;
    static int[] parent, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (T-->0) {
            F = Integer.parseInt(br.readLine());
            nameIdxMap = new HashMap<>();
            parent = new int[2*F];
            cnt = new int[2*F];
            id = 0;
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String n1 = st.nextToken();
                String n2 = st.nextToken();

                if (!nameIdxMap.containsKey(n1)) {
                    cnt[id]++;
                    parent[id] = id;
                    nameIdxMap.put(n1, id++);
                }
                if (!nameIdxMap.containsKey(n2)) {
                    cnt[id]++;
                    parent[id] = id;
                    nameIdxMap.put(n2, id++);
                }

                int i1 = nameIdxMap.get(n1);
                int i2 = nameIdxMap.get(n2);

                union(i1, i2);
                sb.append(cnt[find(i1)]).append("\n");
            }
        }
        System.out.print(sb);
    }

    static int find(int a) {
        if (parent[a] != a) parent[a] = find(parent[a]);
        return parent[a];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        parent[b] = a;
        cnt[a] += cnt[b];
    }
}
