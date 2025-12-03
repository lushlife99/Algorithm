import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 1022 소용돌이 예쁘게 출력하기
 * 시뮬레이션, 구현
 */


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        int maxVal = 0;
        int[][] result = new int[r2 - r1 + 1][c2 - c1 + 1];

        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                int val = getValue(r, c);
                result[r - r1][c - c1] = val;
                maxVal = Math.max(maxVal, val);
            }
        }

        int width = Integer.toString(maxVal).length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                String s = Integer.toString(result[i][j]);
                sb.append(" ".repeat(width - s.length()));
                sb.append(s);
                if (j + 1 < result[0].length) sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static int getValue(int r, int c) {
        int layer = Math.max(Math.abs(r), Math.abs(c));
        int maxValue = (2 * layer + 1) * (2 * layer + 1);

        if (r == layer) {
            return maxValue - (layer - c);
        } else if (c == -layer) {
            return maxValue - (2 * layer) - (layer - r);
        } else if (r == -layer) {
            return maxValue - (4 * layer) - (c + layer);
        } else { // c == layer
            return maxValue - (6 * layer) - (r + layer);
        }
    }
}