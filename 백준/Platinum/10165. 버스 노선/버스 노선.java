import java.io.*;
import java.util.*;

/**
 * boj 10165 버스 노선
 * 그리디, 정렬
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Bus> buses = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (s <= e) {
                buses.add(new Bus(i, s, e));
                buses.add(new Bus(i, s + N, e + N));
            } else {
                buses.add(new Bus(i, s, e + N));
            }
        }

        buses.sort((a, b) -> {
            if (a.s == b.s) return b.e - a.e;
            return a.s - b.s;
        });

        boolean[] dead = new boolean[M + 1];
        int maxEnd = -1;

        for (Bus bus : buses) {
            if (bus.e <= maxEnd) {
                dead[bus.idx] = true;
            } else {
                maxEnd = bus.e;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++) {
            if (!dead[i]) {
                if (sb.length() > 0) sb.append(' ');
                sb.append(i);
            }
        }
        System.out.println(sb.toString());
    }

    static class Bus {
        int idx, s, e;
        Bus(int idx, int s, int e) {
            this.idx = idx;
            this.s = s;
            this.e = e;
        }
    }

}
