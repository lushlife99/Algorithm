import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[] distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        dfs(1, road, distance, K);
        
        for (int i = 1; i < N+1; i++) {
            if (distance[i] <= K) {
                answer += 1;
            }
        }
        return answer;
    }
    
    public void dfs(int node, int[][] road, int[] distance, int k) {
        
        for (int i = 0; i < road.length; i++) {
            if (road[i][0] == node) {
                if (distance[road[i][0]] + road[i][2] < distance[road[i][1]]) {
                    if (distance[road[i][0]] + road[i][2] <= k) {
                        System.out.println(node);
                        distance[road[i][1]] = distance[road[i][0]] + road[i][2];
                        dfs(road[i][1], road, distance, k);
                    }
                }
            }
            
            else if (road[i][1] == node) {
                if (distance[road[i][1]] + road[i][2] < distance[road[i][0]]) {
                    if (distance[road[i][1]] + road[i][2] <= k) {
                        distance[road[i][0]] = distance[road[i][1]] + road[i][2];
                        dfs(road[i][0], road, distance, k);
                    }
                }
            }
        }
    }
}