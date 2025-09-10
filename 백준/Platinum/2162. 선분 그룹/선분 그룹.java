import java.io.*;
import java.util.*;

/**
 * boj 2162 선분 그룹
 * 분리 집합, 수학 (ccw)
 * 
 * ccw 알고리즘 몰라서 못풀고 답지 봄.
 * ccw 알고리즘이 25년 상반기 오토에버 코테에 나왔던걸로 기억.
 * 분리 집합 끝나고 ccw 알고리즘 문제 풀어보기
 */

public class Main {
    
    static class Seg {
        long x1, y1, x2, y2;
        Seg(long x1, long y1, long x2, long y2) {
            this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2;
        }
    }

    static int[] parent, size;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a); b = find(b);
        if (a == b) return;
        // union by size
        if (size[a] < size[b]) {
            int t = a; a = b; b = t;
        }
        parent[b] = a;
        size[a] += size[b];
    }

    static long ccw(long ax, long ay, long bx, long by, long cx, long cy) {
        return (bx - ax) * (cy - ay) - (by - ay) * (cx - ax);
    }

    static boolean between(long a, long b, long v) {
        if (a > b) {
            long t = a; a = b; b = t;
        }
        return a <= v && v <= b;
    }

    static boolean onSegment(Seg s, long x, long y) {
        return between(s.x1, s.x2, x) && between(s.y1, s.y2, y);
    }

    static boolean intersect(Seg a, Seg b) {
        long c1 = ccw(a.x1, a.y1, a.x2, a.y2, b.x1, b.y1);
        long c2 = ccw(a.x1, a.y1, a.x2, a.y2, b.x2, b.y2);
        long c3 = ccw(b.x1, b.y1, b.x2, b.y2, a.x1, a.y1);
        long c4 = ccw(b.x1, b.y1, b.x2, b.y2, a.x2, a.y2);

        if (c1 == 0 && c2 == 0 && c3 == 0 && c4 == 0) {
            boolean xOverlap = Math.max(Math.min(a.x1, a.x2), Math.min(b.x1, b.x2))
                    <= Math.min(Math.max(a.x1, a.x2), Math.max(b.x1, b.x2));
            boolean yOverlap = Math.max(Math.min(a.y1, a.y2), Math.min(b.y1, b.y2))
                    <= Math.min(Math.max(a.y1, a.y2), Math.max(b.y1, b.y2));
            return xOverlap && yOverlap;
        }

        return (c1 > 0 && c2 < 0 || c1 < 0 && c2 > 0 || c1 == 0 && onSegment(a, b.x1, b.y1) || c2 == 0 && onSegment(a, b.x2, b.y2))
                && (c3 > 0 && c4 < 0 || c3 < 0 && c4 > 0 || c3 == 0 && onSegment(b, a.x1, a.y1) || c4 == 0 && onSegment(b, a.x2, a.y2));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        Seg[] segs = new Seg[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x1 = Long.parseLong(st.nextToken());
            long y1 = Long.parseLong(st.nextToken());
            long x2 = Long.parseLong(st.nextToken());
            long y2 = Long.parseLong(st.nextToken());
            segs[i] = new Seg(x1, y1, x2, y2);
        }

        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (intersect(segs[i], segs[j])) union(i, j);
            }
        }

        boolean[] seen = new boolean[N];
        int groups = 0;
        int maxSize = 0;
        for (int i = 0; i < N; i++) {
            int r = find(i);
            if (!seen[r]) {
                seen[r] = true;
                groups++;
                if (size[r] > maxSize) maxSize = size[r];
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(groups).append('\n').append(maxSize).append('\n');
        System.out.print(sb.toString());
    }
}
