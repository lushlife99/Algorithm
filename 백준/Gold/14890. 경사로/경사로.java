import java.io.*;
import java.util.*;

/**
 * boj 14890 경사로
 * 한 라인에서 높이 차이가 1인 블럭의 개수가 L 이상이고, 다음의 높이가 크지 않는 경우
 * 한 라인에서 높이 차이가 1인 블럭의 개수가 2L이상이고, 다음의 높이 차이가 1이 나는 경우
 */

public class Main {

    private static int N, L;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            int[] road = arr[i];
            int[] road2 = new int[N];
            for (int j = 0; j < N; j++) {
                road2[j] = arr[j][i];
            }

            if (valid(road)) answer++;
            if (valid(road2)) answer++;
            
        }

        System.out.println(answer);
    }

    private static boolean valid(int[] road) {

        boolean sig = true;
        for (int i = 0; i < N-1; i++) {
            if (road[i] != road[i+1]) {
                sig = false;
                break;
            }
        }

        if (sig) return true;

        boolean isLower = false;
        int cnt = 1;

        for (int i = 0; i < N-1; i++) {
            if (road[i] == road[i+1]) cnt++;
            else if (road[i] - road[i+1] == 1) {
                if (isLower && cnt < L) return false;

                isLower = true;
                cnt = 1;
            } else if (road[i] - road[i+1] == -1) {
                if (isLower && cnt < 2*L) return false;
                if (!isLower && cnt < L) return false;
                
                isLower = false;
                cnt = 1;
            } else return false;
        }

        if (isLower) {
            if (cnt < L) return false;
        }
        
        return true;
    }

}
