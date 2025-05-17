import java.util.*;

class Solution {
    
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public List<Integer> solution(String[] maps) {
        List<Integer> answers = new ArrayList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if (maps[i].charAt(j) != 'X' && !visited[i][j]) {
                    int sum = dfs(visited, i, j, maps);
                    answers.add(sum);
                }
            }
        }
        
        if (answers.isEmpty()) {
            return List.of(-1);
        }
        
        Collections.sort(answers);
        return answers;
    }
    
    public int dfs(boolean[][] visited, int x, int y, String[] maps) {
        visited[x][y] = true;
        int sum = maps[x].charAt(y) - '0';
        
        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];
            
            if (0 <= cx && cx < maps.length && 0 <= cy && cy < maps[0].length()) {
                if (maps[cx].charAt(cy) != 'X' && !visited[cx][cy]) {
                    sum += dfs(visited, cx, cy, maps);
                }
            }
        }
        
        return sum;
    }
}
