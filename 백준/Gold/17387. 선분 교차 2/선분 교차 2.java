import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long x3 = Long.parseLong(st.nextToken());
        long y3 = Long.parseLong(st.nextToken());
        long x4 = Long.parseLong(st.nextToken());
        long y4 = Long.parseLong(st.nextToken());

        int ccw1 = ccw(x1, y1, x2, y2, x3, y3);
        int ccw2 = ccw(x1, y1, x2, y2, x4, y4);
        int ccw3 = ccw(x3, y3, x4, y4, x1, y1);
        int ccw4 = ccw(x3, y3, x4, y4, x2, y2);

        if (ccw1 * ccw2 <= 0 && ccw3 * ccw4 <= 0) {
            if (ccw1 == 0 && ccw2 == 0 && ccw3 == 0 && ccw4 == 0) {
                if (max(x1, x2) < min(x3, x4)
                        || max(x3, x4) < min(x1, x2)
                        || max(y1, y2) < min(y3, y4)
                        || max(y3, y4) < min(y1, y2)) {
                    System.out.println(0);
                } else {
                    System.out.println(1);
                }
            } else {
                System.out.println(1);
            }
        } else {
            System.out.println(0);
        }

        br.close();
    }

    static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long cross = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        if (cross > 0) return 1;
        if (cross < 0) return -1;
        return 0;
    }

    static long min(long a, long b) { return a < b ? a : b; }
    static long max(long a, long b) { return a > b ? a : b; }
}
