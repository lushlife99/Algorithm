import java.io.*;
import java.util.*;

/**
 * boj 15554 전시회
 * 그리디, 누적합
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] arts = new long[N + 1][2];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arts[i][0] = Long.parseLong(st.nextToken());
            arts[i][1] = Long.parseLong(st.nextToken());
        }
        arts[0][0] = arts[0][1] = 0;
        Arrays.sort(arts, Comparator.comparingLong(a -> a[0]));
        long[] prefix = new long[N + 1];
        prefix[1] = arts[1][1];
        for (int i = 2; i <= N; i++) prefix[i] = prefix[i - 1] + arts[i][1];
        int minIndex = 1;
        long[] answerArr = new long[N + 1];
        answerArr[1] = prefix[1];
        for (int i = 2; i <= N; i++) {
            answerArr[i] = (prefix[i] - prefix[minIndex - 1]) - (arts[i][0] - arts[minIndex][0]);
            if (answerArr[i] <= arts[i][1]) {
                answerArr[i] = arts[i][1];
                minIndex = i;
            }
        }
        long answer = 0;
        for (int i = 1; i <= N; i++) answer = Math.max(answer, answerArr[i]);
        System.out.println(answer);
    }
}
