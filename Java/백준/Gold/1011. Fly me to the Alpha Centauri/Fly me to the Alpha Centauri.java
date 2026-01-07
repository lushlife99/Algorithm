import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * boj 1011 Fly me to the Alpha Centauri
 * dp
 */

public class Main {

    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            int distance = Y - X;

            int max = (int)Math.sqrt(distance);

            if(max == Math.sqrt(distance)) {
                sb.append(max*2-1);
            }
            else if(distance <= max * max + max) {
                sb.append(max*2);
            }
            else {
                sb.append(max*2+1);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}