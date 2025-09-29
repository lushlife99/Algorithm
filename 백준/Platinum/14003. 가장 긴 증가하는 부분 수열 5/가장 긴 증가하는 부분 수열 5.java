import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * boj 14003 가장 긴 증가하는 부분 수열 5
 * 이분탐색, LIS
 */


public class Main {
    private static int N;
    private static int[] arr;
    private static int[] dp;
    private static int[] dpIdx;
    private static int[] prev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N];
        dp = new int[N];
        dpIdx = new int[N];
        prev = new int[N];
        Arrays.fill(prev, -1);

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int lisLength = 0;

        for (int i = 0; i < N; i++) {
            int idx = lowerBound(dp, 0, lisLength, arr[i]);
            dp[idx] = arr[i];
            dpIdx[idx] = i;
            if (idx > 0) prev[i] = dpIdx[idx - 1];
            if (idx == lisLength) lisLength++;
        }

        int cur = dpIdx[lisLength - 1];
        LinkedList<Integer> lisSeq = new LinkedList<>();
        while (cur != -1) {
            lisSeq.addFirst(arr[cur]);
            cur = prev[cur];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(lisLength).append("\n");
        for (int num : lisSeq) sb.append(num).append(" ");

        System.out.println(sb);
    }

    private static int lowerBound(int[] arr, int start, int end, int key) {
        int l = start, r = end;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < key) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}
