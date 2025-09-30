import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 1208 부분수열의 합 2
 * 이분 탐색
 *
 * 2개로 나누고 왼쪽을 기준으로, 하나씩 추가하면서 값을 구함
 */


public class Main {

    private static int N, S;
    private static int[] arr;
    private static List<Long> leftSumList = new ArrayList<>();
    private static List<Long> rightSumList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int mid = N / 2;
        generateSums(0, mid, 0L, leftSumList);
        generateSums(mid, N, 0L, rightSumList);

        Collections.sort(rightSumList);

        long count = 0;
        for (long lSum : leftSumList) {
            long target = S - lSum;
            count += upperBound(rightSumList, target) - lowerBound(rightSumList, target);
        }

        if (S == 0) count--;

        System.out.println(count);
    }

    private static void generateSums(int start, int end, long sum, List<Long> list) {
        if (start == end) {
            list.add(sum);
            return;
        }
        generateSums(start + 1, end, sum + arr[start], list); // 포함
        generateSums(start + 1, end, sum, list);              // 미포함
    }

    private static int lowerBound(List<Long> list, long target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) >= target) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private static int upperBound(List<Long> list, long target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) > target) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}
