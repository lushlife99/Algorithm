import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        boolean[] visited = new boolean[n+1];
        Arrays.fill(visited, true);
        
        for (int l : lost) {
            visited[l] = false;
        }
        
         for (int i = 0; i < reserve.length; i++) {
             if (!visited[reserve[i]]) {
                 visited[reserve[i]] = true;
                 reserve[i] = -1;
             }
             
         }
        
        
        Arrays.sort(reserve);
        
        for (int i = 0; i < reserve.length; i++) {
            
            if (reserve[i] == -1) {
                continue;
            }
            
            if (!visited[reserve[i] - 1]) {
                visited[reserve[i]-1] = true;
            } 
            
            else if (reserve[i] + 1 <= n && !visited[reserve[i] + 1]) {
                visited[reserve[i] + 1] = true;
            }
        }
        
        for (boolean v : visited) {
            if (v) {
                answer += 1;
            }
        }
        
        return answer-1;
    }
}