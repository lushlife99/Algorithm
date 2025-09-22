import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 1305 광고
 * KMP
 */


public class Main {

    private static int L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        String pattern = br.readLine();

        int[] pi = getPi(pattern);
        System.out.println(L - pi[L-1]);

    }

    static int[] getPi(String pattern) {
        int m = pattern.length();
        int[] pi = new int[m];
        int j = 0;

        for (int i = 1; i < m; i++) {
            while(j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j-1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

}


