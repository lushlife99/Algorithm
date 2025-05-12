import java.util.*;

class Solution {
    public int solution(String[] maps) {
        int answer = 0;
        Pair sIndex = null;
        Pair lIndex = null;
        Pair eIndex = null;
        
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        int[][] distance = new int[maps.length][maps[0].length()];
        for (int i = 0; i < maps.length; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                char ch = maps[i].charAt(j);
                if (ch == 'S') {
                    sIndex = new Pair(i,j);
                } else if (ch == 'L') {
                    lIndex = new Pair(i,j);
                } else if (ch == 'E') {
                    eIndex = new Pair(i,j);
                }
            }
        }
        
        distance[sIndex.x][sIndex.y] = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(sIndex.x, sIndex.y));
        
        while (!q.isEmpty()) {
            Pair p = q.poll();
            // System.out.printf("%d %d\n", p.x, p.y);
            if (p.x == lIndex.x && p.y == lIndex.y) {
                break;
            }
            
            for(int i = 0; i < 4; i++) {
                int cx = p.x + dx[i];
                int cy = p.y + dy[i];
                
                if (0 <= cx && cx < maps.length) {
                    if (0 <= cy && cy < maps[cx].length()) {
                        if (maps[cx].charAt(cy) != 'X') {
                            if (distance[p.x][p.y] + 1 < distance[cx][cy]) {
                                q.add(new Pair(cx, cy));
                                distance[cx][cy] = distance[p.x][p.y] + 1;
                            }
                        }
                    }
                }
            }
        }
        
        if (distance[lIndex.x][lIndex.y] == Integer.MAX_VALUE) {
            return -1;
        }
        
        int d1 = distance[lIndex.x][lIndex.y];
        distance = new int[maps.length][maps[0].length()];
        for (int i = 0; i < maps.length; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        
        distance[lIndex.x][lIndex.y] = 0;
        q = new LinkedList<>();
        q.add(new Pair(lIndex.x, lIndex.y));
        
        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (p.x == eIndex.x && p.y == eIndex.y) {
                break;
            }
            
            for(int i = 0; i < 4; i++) {
                int cx = p.x + dx[i];
                int cy = p.y + dy[i];
                
                if (0 <= cx && cx < maps.length) {
                    if (0 <= cy && cy < maps[cx].length()) {
                        if (maps[cx].charAt(cy) != 'X') {
                            if (distance[p.x][p.y] + 1 < distance[cx][cy]) {
                                q.add(new Pair(cx, cy));
                                distance[cx][cy] = distance[p.x][p.y] + 1;
                            }
                        }
                    }
                }
            }
        }
        
        if (distance[eIndex.x][eIndex.y] == Integer.MAX_VALUE) {
            return -1;
        }
        
        return d1 + distance[eIndex.x][eIndex.y];
    }
    
    class Pair {
        public int x;
        public int y;
        
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}