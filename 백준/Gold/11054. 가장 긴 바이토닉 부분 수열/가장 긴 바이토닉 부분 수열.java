import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static Integer[] dp_right;
    static Integer[] dp_left;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp_right = new Integer[N];
        dp_left = new Integer[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++) {
            solution_right(i);
            solution_left(i);
        }

        int max = -1;

        for(int i = 0; i < N; i++) {
            max = Math.max(max, (dp_right[i]+dp_left[i]-1));
        }

        System.out.println(max);
        br.close();
    }


    static int solution_right(int depth) {

        if(dp_right[depth] == null) {
            dp_right[depth] = 1;

            for(int i = depth - 1; i >= 0; i--) {
                if(arr[i] < arr[depth]) {
                    dp_right[depth] = Math.max(dp_right[depth], solution_right(i) + 1);
                }
            }
        }
        return dp_right[depth];
    }

    static int solution_left(int depth) {

        if(dp_left[depth] == null) {
            dp_left[depth] = 1;    // 1로 초기화

            for(int i =  depth + 1; i < dp_left.length; i++) {
                if(arr[i] < arr[depth]) {
                    dp_left[depth] = Math.max(dp_left[depth], solution_left(i) + 1);
                }
            }
        }
        return dp_left[depth];
    }
}