import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 1111 IQ Test
 */


public class Main {

    static int N;
    static int[] arr;
    static int a,b;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (N < 3) {
            if (N == 2 && arr[0] == arr[1]) {
                System.out.println(arr[0]);
                return;
            }
            System.out.println("A");
            return;
        }

        boolean isZero = false;
        boolean allEq = true;
        for (int i = 0; i < N - 1; i++) {
            if (arr[i] - arr[i+1] == 0) {
                isZero = true;
            }

            if (i != 0 && arr[i] != arr[i+1]) {
                allEq = false;
            }
        }

        if (isZero) {
            if (allEq) {
                System.out.println(arr[N-1]);
                return;
            } else {
                System.out.println("B");
                return;
            }
        }

        if (((arr[1] - arr[2]) % (arr[0] - arr[1])) != 0) {
            System.out.println("B");
            return;
        }

        int a  = (arr[1] - arr[2]) / (arr[0] - arr[1]);
        int b = arr[1] - arr[0] * a;

        for (int i = 0; i < N - 1; i++) {
            if (arr[i] * a + b != arr[i+1]) {
                System.out.println("B");
                return;
            }
        }

        System.out.println(arr[N-1] * a + b);
    }

}

