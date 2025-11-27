import java.io.*;
import java.util.*;

/**
 * boj 1114 통나무 자르기
 * 메개 변수 탐색 O(K*logL)
 */


public class Main {

    private static int L, K, C;
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[K+2];
        arr[0] = 0;
        arr[K+1] = L;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int start = 0;
        int end = L;
        int answerFirstCut = 0;
        int answer = L;

        while (start <= end) {
            int mid = (start + end) / 2;
            int firstCut = 0;
            int last = L;
            int cnt = 0;

            for (int i = K; i >= 0; i--) {
                if (last - arr[i] > mid) {
                    cnt++;
                    last = arr[i+1];
                    firstCut = arr[i+1];

                    if (last - arr[i] > mid) { // 불가능
                        cnt = C+1;
                        break;
                    }
                }
            }

            if (cnt > C) { // 불가능
                start = mid+1;
            } else {
                if (cnt < C) {
                    firstCut = arr[1];
                }
                end = mid-1;
                if (answer > mid) {
                    answerFirstCut = firstCut;
                    answer = mid;
                }
            }
        }

        System.out.println(answer + " " + answerFirstCut);
    }
}