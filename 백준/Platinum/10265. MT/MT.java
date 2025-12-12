import java.io.*;
import java.util.*;

public class Main {

    static int N, K, cnt;
    static int[] nxt;
    static boolean[] visited;
    static int[] done;
    static int[] cycle;

    static class ComponentInfo {
        int cycleSize;
        int totalSize;

        public ComponentInfo() {
            this.cycleSize = 0;
            this.totalSize = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nxt = new int[N + 1];
        visited = new boolean[N + 1];
        done = new int[N + 1];
        cycle = new int[N + 1];
        cnt = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nxt[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        List<ComponentInfo> components = new ArrayList<>();
        for (int i = 0; i <= cnt; i++) {
            components.add(new ComponentInfo());
        }

        for (int i = 1; i <= N; i++) {
            if (cycle[i] > 0) {
                components.get(cycle[i]).cycleSize++;
            }

            components.get(done[i]).totalSize++;
        }

        boolean[] dt = new boolean[K + 1];
        dt[0] = true;

        for (int i = 1; i <= cnt; i++) {
            int minSize = components.get(i).cycleSize;
            int maxSize = components.get(i).totalSize;

            if (minSize > K) continue;

            for (int j = K; j >= minSize; j--) {
                for (int w = minSize; w <= maxSize; w++) {
                    if (j >= w) {
                        if (dt[j - w]) {
                            dt[j] = true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = K; i >= 0; i--) {
            if (dt[i]) {
                ans = i;
                break;
            }
        }

        System.out.println(ans);
    }

    static int dfs(int cur) {
        visited[cur] = true;

        if (!visited[nxt[cur]]) {
            return done[cur] = dfs(nxt[cur]);
        } else if (done[nxt[cur]] == 0) {
            cnt++;
            cycle[cur] = cnt;

            for (int node = nxt[cur]; node != cur; node = nxt[node]) {
                cycle[node] = cnt;
            }
            return done[cur] = cnt;
        } else {
            return done[cur] = done[nxt[cur]];
        }
    }
}