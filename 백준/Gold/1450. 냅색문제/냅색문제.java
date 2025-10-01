import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 1450 냅색문제
 * Meet in the Middle
 */

public class Main {

    private static int N, C;
    private static List<Integer> weight = new ArrayList<>();
    private static List<Long> left = new ArrayList<>();
    private static List<Long> right = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int w = Integer.parseInt(st.nextToken());
            if (w <= C) weight.add(w);
        }

        combine(0, weight.size() / 2 - 1, left, 0);
        combine(weight.size() / 2, weight.size() - 1, right, 0);

        Collections.sort(right);

        long ans = 0;
        for (long l : left) {
            int idx = upperBound(right, C - l);
            if (idx >= 0) ans += (idx + 1);
        }

        System.out.println(ans);
    }

    private static void combine(int start, int end, List<Long> sums, long sum) {
        if (sum > C) return;

        if (start > end) {
            sums.add(sum);
            return;
        }

        combine(start + 1, end, sums, sum);
        combine(start + 1, end, sums, sum + weight.get(start));
    }

    private static int upperBound(List<Long> sums, long target) {
        int left = 0;
        int right = sums.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (sums.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left - 1;
    }
}
