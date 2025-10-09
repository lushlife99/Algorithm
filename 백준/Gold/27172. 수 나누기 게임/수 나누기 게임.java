import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] cards = new int[N];
        int MAX = 1_000_000;
        long[] scoreByValue = new long[MAX + 1];
        boolean[] exists = new boolean[MAX + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            exists[cards[i]] = true;
        }

        for (int i = 0; i < N; i++) {
            int x = cards[i];
            for (int mul = 2 * x; mul <= MAX; mul += x) {
                if (exists[mul]) {
                    scoreByValue[mul] -= 1;
                    scoreByValue[x] += 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(scoreByValue[cards[i]]);
            if (i < N - 1) sb.append(' ');
        }
        System.out.println(sb.toString());
    }
}
