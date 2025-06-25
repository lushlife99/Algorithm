import java.util.*;

class Solution {

    private static boolean[][] available;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        int row = storage.length;
        int col = storage[0].length();
        available = new boolean[row][col];

        for (String request : requests) {
            
            if (request.length() == 1) {
                
                for (int i = 0; i < row; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < col; j++) {
                        
                        if (storage[i].charAt(j) != request.charAt(0)) {
                            sb.append(storage[i].charAt(j));
                            continue;
                        }
                        
                        boolean sig = true;
                        
                        if (0 == i || row - 1 == i || 0 == j || col - 1 == j) {
                            sb.append("0");
                        }
                        
                        else {
                            for (int k = 0; k < 4; k++) {
                                int nx = i + dx[k];
                                int ny = j + dy[k];

                                if (0 <= nx && nx < row && 0 <= ny && ny < col) {
                                    if (available[nx][ny]) {
                                        sb.append("0");
                                        sig = false;
                                        break;
                                    }
                                }
                            }
                            if (sig) {
                                sb.append(storage[i].charAt(j));
                            }
                        }
                    }
                    storage[i] = sb.toString();
                }
            } else {
                for (int i = 0; i < row; i++) {
                    StringBuilder sb = new StringBuilder();
                    
                    for (int j = 0; j < col; j++) {
                        if (storage[i].charAt(j) == request.charAt(0)) {
                            sb.append("0");
                        } else {
                            sb.append(storage[i].charAt(j));
                        }
                    }
                    storage[i] = sb.toString();
                }
            }
            available = new boolean[row][col];
            updateAvailable(storage);
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (storage[i].charAt(j) != '0') answer++;
            }
        }
        return answer;
    }

    public void updateAvailable(String[] storage) {
        Queue<Pair> q = new LinkedList<>();
        int row = storage.length;
        int col = storage[0].length();
        
        for (int i = 0; i < row; i++) {
            if (storage[i].charAt(0) == '0') {
                q.add(new Pair(i, 0));
            }
            
            if (storage[i].charAt(col-1) == '0') {
                q.add(new Pair(i, col-1));
            }
        }
        
        for (int j = 0; j < col; j++) {
            if (storage[0].charAt(j) == '0') {
                q.add(new Pair(0, j));
            }
            
            if (storage[row-1].charAt(j) == '0') {
                q.add(new Pair(row-1, j));
            }
        }
        
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            
            if (available[pair.x][pair.y]) continue;
            
            available[pair.x][pair.y] = true;
            
            for (int i = 0; i < 4; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];
                
                if (0 <= nx && nx < row && 0 <= ny && ny < col) {
                    if (!available[nx][ny] && storage[nx].charAt(ny) == '0') {
                        q.add(new Pair(nx, ny));
                    }
                }
            }
            
        }
        
        
    }
    
    static class Pair {
        int x;
        int y;
        
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
}
