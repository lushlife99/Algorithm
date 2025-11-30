import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * boj 2613 숫자구슬
 * 매개변수 탐색
 */

public class Main {

    private static int N, M;
    private static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 1;
        int right = N*100;

        int answer = Integer.MAX_VALUE;
        List<Integer> answerSegments = new ArrayList<>();

        while (left <= right) {
            int mid = (left + right) / 2;
            int sum = 0;
            int total = 0;
            List<Integer> segments = new ArrayList<>();
            int cnt = 0;
            int max = 0;
            for (int i = 0; i < N; i++) {

                if (arr[i] > mid) {
                    cnt = M+1;
                    break;
                }

                if (sum + arr[i] > mid) {
                    segments.add(total);
                    total = 1;
                    cnt++;

                    max = Math.max(max, sum);
                    sum = arr[i];

                } else {
                    total++;
                    sum += arr[i];
                }
            }

            max = Math.max(max, sum);
            segments.add(total);
            cnt++;

            if (cnt > M) {
                left = mid+1;
            } else {
                right = mid-1;
                if (answer > max) {
                    answer = max;
                    answerSegments.clear();
                    answerSegments.addAll(segments);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer).append("\n");

        if (answerSegments.size() != M) {
            int cnt = M - answerSegments.size();
            for (int i = answerSegments.size() - 1; i >= 0; i--) {
                while (cnt > 0 && answerSegments.get(i) > 1) {
                    answerSegments.set(i, answerSegments.get(i) - 1);
                    cnt--;
                }
            }

            for (Integer answerSegment : answerSegments) {
                sb.append(answerSegment).append(" ");
            }

            for (int i = 0; i < M - answerSegments.size(); i++) {
                sb.append(1).append(" ");
            }
        } else {
            for (Integer answerSegment : answerSegments) {
                sb.append(answerSegment).append(" ");
            }
        }

        System.out.println(sb);
    }
}

