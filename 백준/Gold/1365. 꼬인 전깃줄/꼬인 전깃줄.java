import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int[] dp = new int[N];
        int length = 0;

        for (int i = 0; i < N; i++) {
            int x = arr[i];
            int pos = Arrays.binarySearch(dp, 0, length, x);
            if (pos < 0) {
                pos = -pos - 1;
            }
            dp[pos] = x;
            if (pos == length) {
                length++;
            }
        }

        int result = N - length;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(result));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
