import java.io.*;
import java.util.*;


/**
 * boj 2457 공주님의 정원
 * 그리디
 *
 * 개화 시기로, 폐화시기로 정렬
 */

/**
 * 반례
 * 1. 경계값
 */

//3
//1 1 5 31
//1 1 11 30
//11 30 12 30

public class Main {

    static class Flower implements Comparable<Flower>{
        Date start;
        Date end;

        public Flower(Date start, Date end) {
            this.start = start; this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if (this.start.month != o.start.month) {
                return this.start.month - o.start.month;
            } else if (this.start.day != o.start.day) {
                return this.start.day - o.start.day;
            }

            if (this.end.month != o.end.month) {
                return o.end.month - this.end.month;
            }

            return o.end.day - this.end.day;
        }
    }

    static class Date implements Comparable<Date> {
        int month;
        int day;

        public Date(int month, int day) {
            this.month = month; this.day = day;
        }

        @Override
        public int compareTo(Date o) {
            if (this.month != o.month) {
                return this.month - o.month;
            }

            return this.day - o.day;
        }

        @Override
        public String toString() {
            return "month : " + this.month + ", day : " + day;
        }
    }

    private static int N;
    private static List<Flower> flowers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sM = Integer.parseInt(st.nextToken());
            int sD = Integer.parseInt(st.nextToken());
            int eM = Integer.parseInt(st.nextToken());
            int eD = Integer.parseInt(st.nextToken());

            Date start = new Date(sM, sD);
            Date end = new Date(eM, eD);

            flowers.add(new Flower(start, end));
        }

        Collections.sort(flowers);
        Date current = new Date(3, 1);
        Date e = new Date(11, 30);

        int cnt = 0;
        Queue<Flower> queue = new PriorityQueue<>((f1, f2) -> {
            return f2.end.compareTo(f1.end);
        });

        for (Flower f : flowers) {

            if (current.compareTo(f.end) > 0)  continue;

            if (current.compareTo(f.start) < 0) { // current f.s
                if (queue.isEmpty()) {
                    System.out.println(0);
                    return;
                } else {
                    Flower latest = queue.poll();
                    cnt++;
                    current = latest.end;

                    if (current.compareTo(e) > 0) {
                        break;
                    }

                    if (current.compareTo(f.start) < 0) {
                        System.out.println(0);
                        return;
                    }
                    queue.clear();
                }
            }

            queue.offer(f);
        }

        if (!queue.isEmpty() && current.compareTo(e) <= 0) {
            Flower f = queue.poll();
            if (f.end.compareTo(e) <= 0) {
                System.out.println(0);
                return;
            }
            cnt++;
        }

        System.out.println(cnt);
    }
}


