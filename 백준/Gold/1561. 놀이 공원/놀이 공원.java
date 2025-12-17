import java.io.*;
import java.util.*;

/**
 * boj 1561 놀이 공원
 *
 * 1. 운행 시간 구하기
 * 2. 해당 운행 시간으로 마지막 놀이기구 번호 구하기
 */

/**
 * 반례
 */

//6 5
//2 1 1 1 1

public class Main {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (N <= M) {
            System.out.println(N);
            return;
        }
        arr = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long time = binarySearch();

        long prevCount = M;
        for (int i = 0; i < M; i++) {
            prevCount += (time - 1) / arr[i];
        }

        for (int i = 0; i < M; i++) {
            if (time % arr[i] == 0) {
                prevCount++;
                if (prevCount == N) {
                    System.out.println(i + 1);
                    return;
                }
            }
        }
    }

    static long binarySearch() {
        long s = 0;
        long e = 2000000000L * 30L;
        long result = 0;

        while (s <= e) {
            long mid = (s + e) / 2;
            long cnt = M;

            for (int i = 0; i < M; i++) {
                cnt += mid / arr[i];
                if (cnt >= N) break;
            }

            if (cnt >= N) {
                result = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return result;
    }
}

