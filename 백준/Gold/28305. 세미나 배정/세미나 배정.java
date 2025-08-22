import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * boj 28305 세미나 배정
 * 그리디, 이분탐색
 */


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        int lo = 0, hi = N + 1;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            boolean sig = true;
            for (int i = 0; i < N; i++) {
                if (pq.size() < mid) {
                    pq.add(Math.max(arr[i] - T + 1, 1));
                } else {
                    int start = pq.poll() + T;
                    int assigned = Math.max(start, arr[i] - T + 1);
                    if (assigned > arr[i]) {
                        sig = false;
                        break;
                    }
                    pq.add(assigned);
                }
            }
            if (sig) hi = mid;
            else lo = mid;
        }
        System.out.println(hi);
    }
}

