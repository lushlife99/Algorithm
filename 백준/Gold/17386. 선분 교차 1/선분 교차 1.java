import java.io.*;
import java.util.StringTokenizer;


/**
 * boj 17386 선분 교차 1
 * ccw
 */


public class Main {

    static class Line {

        long x1, y1, x2, y2;

        public Line(long x1, long y1, long x2, long y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Line[] lines = new Line[2];
        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x1 = Long.parseLong(st.nextToken());
            long y1 = Long.parseLong(st.nextToken());
            long x2 = Long.parseLong(st.nextToken());
            long y2 = Long.parseLong(st.nextToken());
            lines[i] = new Line(x1, y1, x2, y2);
        }

        int res1 = ccw(lines[0].x1, lines[0].y1, lines[0].x2, lines[0].y2, lines[1].x1, lines[1].y1)
                * ccw(lines[0].x1, lines[0].y1, lines[0].x2, lines[0].y2, lines[1].x2, lines[1].y2);

        int res2 = ccw(lines[1].x1, lines[1].y1, lines[1].x2, lines[1].y2, lines[0].x1, lines[0].y1)
                * ccw(lines[1].x1, lines[1].y1, lines[1].x2, lines[1].y2, lines[0].x2, lines[0].y2);

        if (res1 == -1 && res2 == -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        return x1*y2 + x2*y3 + x3*y1 - x2*y1 - x3*y2 - x1*y3 < 1 ? -1 : 1;
    }
}