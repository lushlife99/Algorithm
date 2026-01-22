import java.io.*;
import java.util.*;

/**
 * boj 14002 가장 긴 증가하는 부분 수열 4
 * LIS
 */

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = new int[N];
        int[] pos = new int[N];
        int[] parent = new int[N];

        Arrays.fill(parent, -1);

        int length = 0;

        for (int i = 0; i < N; i++) {
            int idx = lowerBound(lis, length, arr[i]);
            lis[idx] = arr[i];
            pos[i] = idx;

            if (idx > 0) {
                for (int j = i - 1; j >= 0; j--) {
                    if (pos[j] == idx - 1 && arr[j] < arr[i]) {
                        parent[i] = j;
                        break;
                    }
                }
            }

            if (idx == length) {
                length++;
            }
        }

        Stack<Integer> stack = new Stack<>();
        int last = -1;

        for (int i = N - 1; i >= 0; i--) {
            if (pos[i] == length - 1) {
                last = i;
                break;
            }
        }

        while (last != -1) {
            stack.push(arr[last]);
            last = parent[last];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(length).append('\n');
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(' ');
        }

        System.out.println(sb);
    }

    static int lowerBound(int[] lis, int size, int val) {
        int left = 0, right = size;

        while (left < right) {
            int mid = (left + right) / 2;
            if (lis[mid] >= val) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}