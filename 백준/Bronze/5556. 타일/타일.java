import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine().trim());
        int K = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            long distLeft = x;
            long distRight = N - x + 1;
            long distTop = y;
            long distBottom = N - y + 1;

            long dist = Math.min(Math.min(distLeft, distRight),
                    Math.min(distTop, distBottom));

            int color = (int)((dist - 1) % 3) + 1;

            sb.append(color).append('\n');
        }
        System.out.print(sb);
    }
}
