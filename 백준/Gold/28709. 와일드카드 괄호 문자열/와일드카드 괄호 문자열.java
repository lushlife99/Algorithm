import java.io.*;

/**
 * boj 28709 와일드카드 문자열
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String s = br.readLine().trim();
            int firstStar = s.indexOf('*');
            if (firstStar == -1) {
                if (noStar(s)) {
                    bw.write("YES\n");
                } else {
                    bw.write("NO\n");
                }
            } else {
                int lastStar = s.lastIndexOf('*');
                String left = s.substring(0, firstStar);
                String right = s.substring(lastStar + 1);
                if ((left.isEmpty() || check(left, false)) && (right.isEmpty() || check(right, true))) {
                    bw.write("YES\n");
                } else {
                    bw.write("NO\n");
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static boolean noStar(String s) {
        int n = s.length();
        if ((n & 1) == 1) return false;
        if (s.charAt(0) == ')' || s.charAt(n - 1) == '(') return false;
        int open = 0, close = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') open++;
            else if (c == ')') close++;
        }
        int need = n / 2;
        if (open > need || close > need) return false;

        int curOpen = open, bal = 0;
        for (char c : s.toCharArray()) {
            char t = c;
            if (c == '?') {
                if (curOpen < need) { t = '('; curOpen++; }
                else t = ')';
            }
            bal += (t == '(') ? 1 : -1;
            if (bal < 0) return false;
        }
        return bal == 0;
    }

    static boolean check(String s, boolean rev) {
        int cnt = 0;
        if (!rev) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                cnt += (c == ')') ? -1 : 1;
                if (cnt < 0) return false;
            }
        } else {
            for (int i = s.length() - 1; i >= 0; i--) {
                char c = s.charAt(i);
                cnt += (c == '(') ? -1 : 1;
                if (cnt < 0) return false;
            }
        }
        return true;
    }
}
