import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 14891 톱니바퀴
 * 시뮬레이션
 */

public class Main {

    static int[][] gear = new int[4][8];
    static int[] top = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = s.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()); // 1: 시계, -1: 반시계
            rotate(idx, dir);
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            if (gear[i][top[i]] == 1) {
                answer += (1 << i);
            }
        }

        System.out.println(answer);
    }

    static void rotate(int idx, int dir) {
        int[] move = new int[4];
        move[idx] = dir;

        // 오른쪽 전파
        for (int i = idx + 1; i < 4; i++) {
            int leftGear = i - 1;

            int leftContact = gear[leftGear][(top[leftGear] + 2) % 8];
            int rightContact = gear[i][(top[i] + 6) % 8];

            if (leftContact == rightContact) break;

            move[i] = -move[leftGear];
        }

        // 왼쪽 전파
        for (int i = idx - 1; i >= 0; i--) {
            int rightGear = i + 1;

            int rightContact = gear[rightGear][(top[rightGear] + 6) % 8];
            int leftContact = gear[i][(top[i] + 2) % 8];

            if (leftContact == rightContact) break;

            move[i] = -move[rightGear];
        }

        // 실제 회전 적용
        for (int i = 0; i < 4; i++) {
            if (move[i] != 0) {
                top[i] = (top[i] - move[i] + 8) % 8;
            }
        }
    }
}
