import java.io.*;
import java.util.*;


/**
 * boj 3190 뱀
 * 시뮬레이션
 */

public class Main {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x; this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            Point oP = (Point) o;
            return this.x == oP.x && this.y == oP.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return this.x + " " + this.y;
        }
    }

    static class Node {
        Point p;
        Node next;

        public Node(int x, int y) {
            this.p = new Point(x, y);
        }
    }

    private static int N, K, L;
    private static int[][] directions = {{1,0},{0,1}, {-1,0},{0,-1}};
    private static Set<Point> apple = new HashSet<>();
    private static Set<Point> used = new HashSet<>();
    private static Map<Integer, Integer> directionChangeMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            apple.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        L = Integer.parseInt(br.readLine());

        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int changeTo = st.nextToken().equals("D") ? 3 : 1;
            directionChangeMap.put(time, changeTo);
        }

        Node head = new Node(1,1);
        Node tail = head;
        used.add(new Point(1,1));
        int time = 0;
        int curDir = 1;

        while(true) {
            time++;

            // 뱀 이동
            int nx = head.p.x + directions[curDir][0];
            int ny = head.p.y + directions[curDir][1];

            if (nx == 0 || ny == 0 || nx == N+1 || ny == N+1) {
                break;
            }
            Point np = new Point(nx, ny);
            if (apple.contains(np)) {
                apple.remove(np);

                Node newNode = new Node(nx, ny);
                head.next = newNode;
                head = newNode;

                if (used.contains(np)) {
                    break;
                }
                used.add(np);

            } else {
                Node newNode = new Node(nx, ny);
                head.next = newNode;
                head = newNode;

                if (used.contains(np)) {
                    break;
                }
                used.add(np);
                used.remove(tail.p);
                tail = tail.next;
            }



            if (directionChangeMap.containsKey(time)) {
                curDir = (curDir + directionChangeMap.get(time)) % directions.length;
            }
        }

        System.out.println(time);
    }
}
