import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 1786 찾기
 * KMP
 */


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        String pattern = br.readLine();

        List<Integer> res = kmp(text, pattern);

        StringBuilder sb = new StringBuilder();
        sb.append(res.size()).append("\n");

        for (int pos : res) {
            sb.append(pos).append(" ");
        }
        System.out.println(sb.toString());
    }

    static int[] getPi(String pattern) {
        int[] pi = new int[pattern.length()];
        int j = 0;

        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

    static List<Integer> kmp(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int[] pi = getPi(pattern);

        int n = text.length();
        int m = pattern.length();
        int j = 0;

        for (int i = 0; i < n; i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = pi[j-1];
            }

            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == m - 1) {
                    result.add(i - m + 2);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }

        return result;
    }

}


