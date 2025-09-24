import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * boj 3356 라디오 전송
 * 문자열, KMP
 */


public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine().trim());
        String s = br.readLine().trim();
        int[] pi = getPi(s);
        int longest = pi[L - 1];
        int result = L - longest;
        System.out.println(result);
    }

    public static int[] getPi(String s) {
        int n = s.length();
        int[] pi = new int[n];
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
                pi[i] = j;
            }
        }
        return pi;
    }
}



