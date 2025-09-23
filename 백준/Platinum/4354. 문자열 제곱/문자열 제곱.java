import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 4354 문자열 제곱
 * 문자열, KMP
 *
 * 반례
 * ababa
 */


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String pattern = br.readLine();
            if (pattern.equals(".")) break;
            int[] pi = getPi(pattern);

            String answer = "1";
            if (pi.length % (pi.length - pi[pi.length-1]) == 0) {
                answer = String.valueOf(pi.length / (pi.length - pi[pi.length-1]));
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int[] getPi(String pattern) {
        int m = pattern.length();
        int[] pi = new int[m];
        int j = 0;

        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j-1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

}


