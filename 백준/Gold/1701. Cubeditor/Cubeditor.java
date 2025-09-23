import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 1701 Cubeditor
 * 문자열, KMP
 */


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(i);
            int[] pi = getPi(sub);

            for (int val : pi) {
                max = Math.max(max, val);
            }
        }

        System.out.println(max);
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


