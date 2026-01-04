import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 32288 바코드 닉네임
 */


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String s = br.readLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                sb.append('i');
            } else if (s.charAt(i) == 'l') {
                sb.append("L");
            }
        }

        System.out.println(sb);
    }
}
