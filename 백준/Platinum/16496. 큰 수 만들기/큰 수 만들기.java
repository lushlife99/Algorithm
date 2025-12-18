import java.io.*;
import java.util.*;

/**
 * boj 16496 큰 수 만들기
 */

/**
 * 80% 틀림
 * 반례 찾기
 * 3
 * 353 35 34
 * 답 : 3535334
 * 오답 : 3533534
 */


public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<String> li = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            li.add(st.nextToken());
        }

        Collections.sort(li, (s1, s2) -> {
            int l1 = s1.length();
            int l2 = s2.length();

            if (l1 == l2) return s2.compareTo(s1);

            if (l1 > l2) {

                for (int i = 0; i < l2; i++) {
                    if (s1.charAt(i) != s2.charAt(i)) return s2.charAt(i) - s1.charAt(i);
                }

                for (int i = 0; i < Math.min(l1-l2, l2); i++) {
                    if (s1.charAt(i+l2) != s2.charAt(i)) return s2.charAt(i) - s1.charAt(i+l2);
                }

            } else {
                for (int i = 0; i < l1; i++) {
                    if (s1.charAt(i) != s2.charAt(i)) return s2.charAt(i) - s1.charAt(i);
                }

                for (int i = 0; i < Math.min(l2-l1, l1); i++) {
                    if (s1.charAt(i) != s2.charAt(i+l1)) return s2.charAt(i+l1) - s1.charAt(i);
                }
            }

            long v1 = Long.parseLong(s1+s2);
            long v2 = Long.parseLong(s2+s1);

            return Long.compare(v2, v1);
        });

        if (li.get(0).charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String s : li) {
            sb.append(s);
        }

        System.out.print(sb);
    }

}

