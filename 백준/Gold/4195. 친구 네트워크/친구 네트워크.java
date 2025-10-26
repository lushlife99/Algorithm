import java.io.*;
import java.util.*;

/**
 * boj 4195 친구 네트워크
 * 유니온 파인드
 */


public class Main {

    private static int T, F;
    private static Map<String, String> parent;
    private static Map<String, Integer> cntMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            F = Integer.parseInt(br.readLine());
            parent = new HashMap<>();
            cntMap = new HashMap<>();

            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String a = st.nextToken();
                String b = st.nextToken();

                parent.putIfAbsent(a, a);
                parent.putIfAbsent(b, b);
                cntMap.putIfAbsent(a, 1);
                cntMap.putIfAbsent(b, 1);

                union(a, b);
                sb.append(cntMap.get(find(a))).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static String find(String a) {
        if (!parent.get(a).equals(a)) {
            parent.put(a, find(parent.get(a)));
        }

        return parent.get(a);
    }

    private static void union(String a, String b) {
        String ra = find(a);
        String rb = find(b);

        if (ra.equals(rb)) return;

        if (ra.compareTo(rb) > 0) {
            parent.put(rb, ra);
            cntMap.put(ra, cntMap.get(rb) + cntMap.get(ra));
        } else {
            parent.put(ra, rb);
            cntMap.put(rb, cntMap.get(rb) + cntMap.get(ra));
        }

    }
}

