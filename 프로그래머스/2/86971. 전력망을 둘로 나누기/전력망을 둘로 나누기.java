import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        for (int i = 0; i < wires.length; i++) {
            int a = bfs(wires, wires[i][0], i, n);
            int b = bfs(wires, wires[i][1], i, n);
            answer = Math.min(Math.abs(a-b), answer);
            
        }
        return answer;
    }
    
    public int bfs(int[][] wires, int start, int removeWireIdx, int n) {
    
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        q.add(start);
        visited[start] = true;
        int cnt = 1;
        
        
        while (!q.isEmpty()) {
            int current = q.poll();
            
            for (int i = 0; i < wires.length; i++) {
                
                if (i == removeWireIdx) {
                    continue;
                }
                
                if (visited[wires[i][0]] && !visited[wires[i][1]]) {
                    visited[wires[i][1]] = true;
                    q.add(wires[i][1]);
                    cnt++;
                }
                
                else if (!visited[wires[i][0]] && visited[wires[i][1]]) {
                    visited[wires[i][0]] = true;
                    q.add(wires[i][0]);
                    cnt++;
                }
            }
        }
        
        return cnt;
    }
}