import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 서버 증설 시간 저장
        
        for (int i = 0; i < players.length; i++) {
            while (!pq.isEmpty() && pq.peek() == i) {
                pq.poll();
            }
            
            int addServerCount = players[i] / m - pq.size();
            while (addServerCount > 0) {
                pq.add(i+k);
                answer += 1;
                addServerCount -= 1;
            }
        }
        
        
        return answer;
    }
}