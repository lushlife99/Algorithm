import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * boj 2437 저울
 * 그리디
 */


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        long sum = 0;
        for (int w : arr) {
            if (w > sum + 1) break;
            sum += w;
        }

        System.out.println(sum + 1);
    }
}



