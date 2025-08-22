import java.io.*;
import java.util.*;

/**
 * boj 9576 책 나눠주기
 * 그리디
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            List<int[]> list = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list.add(new int[]{a, b});
            }

            list.sort((o1, o2) -> {
                if (o1[1] != o2[1]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            });

            boolean[] used = new boolean[N + 1];
            int count = 0;

            for (int[] req : list) {
                int a = req[0], b = req[1];
                for (int x = a; x <= b; x++) {
                    if (!used[x]) {
                        used[x] = true;
                        count++;
                        break;
                    }
                }
            }

            sb.append(count).append('\n');
        }

        System.out.print(sb.toString());
    }
}
