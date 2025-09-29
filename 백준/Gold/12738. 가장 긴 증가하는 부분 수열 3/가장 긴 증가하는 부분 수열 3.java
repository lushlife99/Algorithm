import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * boj 12015 가장 긴 증가하는 부분 수열 2
 * 이분탐색, LIS
 */


public class Main {

    private static int N;
    private static int[] arr, dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = IntStream.range(0, N)
                .map(i -> Integer.parseInt(st.nextToken()))
                .toArray();
        dp = new int[N];
        Arrays.fill(dp, Integer.MIN_VALUE);
        int lis = 0;

        for (int i = 0; i < N; i++) {
            int idx = binarySearch(arr[i], 0, lis, lis +1);

            if (idx == -1) {
                dp[lis++] = arr[i];
            }
            else {
                dp[idx] = arr[i];
            }
        }

        System.out.println(lis);
    }

    private static int binarySearch(int num, int start, int end, int size) {
        int res = 0;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (num <= dp[mid]) {
                res = mid;
                end = mid - 1;
            }

            else {
                start = mid + 1;
            }
        }

        if (start == size) {
            return -1;
        }
        else {
            return res;
        }
    }
}
