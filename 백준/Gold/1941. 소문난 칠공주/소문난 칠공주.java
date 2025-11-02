import java.io.*;
import java.util.*;

/**
 * boj 1941 소문난 칠공주
 * bfs
 */

/**
 * 반례 생각하기
 */


public class Main {

    static class Point {
        int x, y;
        public Point(int x, int y) { this.x = x; this.y = y; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point p = (Point) o;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() { return Objects.hash(x, y); }
    }

    static char[][] arr = new char[5][5];
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static Set<Set<Point>> answers = new HashSet<>();
    static Set<Integer> selected = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) arr[i] = br.readLine().toCharArray();

        combination(0, 0);
        System.out.println(answers.size());
    }

    static void combination(int idx, int depth) {
        if (depth == 7) {
            Set<Point> group = new HashSet<>();
            for (int i : selected) {
                group.add(new Point(i / 5, i % 5));
            }
            
            if (isConnected(group) && isValid(group)) answers.add(group);
            return;
        }

        for (int i = idx; i < 25; i++) {
            selected.add(i);
            combination(i + 1, depth + 1);
            selected.remove(i);
        }
    }

    static boolean isConnected(Set<Point> group) {
        Queue<Point> q = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        Point start = group.iterator().next();
        q.add(start);
        visited.add(start);

        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int[] d : dir) {
                Point next = new Point(cur.x + d[0], cur.y + d[1]);
                if (group.contains(next) && !visited.contains(next)) {
                    visited.add(next);
                    q.add(next);
                }
            }
        }
        return visited.size() == 7;
    }

    static boolean isValid(Set<Point> group) {
        int sCount = 0;
        for (Point p : group) if (arr[p.x][p.y] == 'S') sCount++;
        return sCount >= 4;
    }
}

