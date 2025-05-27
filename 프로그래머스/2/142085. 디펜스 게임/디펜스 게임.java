import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        long sum = 0;
        long sum2 = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((n1 ,n2) -> {
            return enemy[n1] - enemy[n2];
        });
        
        for (int i = 0; i < enemy.length; i++) {
            
            if (pq.size() < k) {
                pq.add(i);
                sum += enemy[i];
            }
            
            else if (enemy[pq.peek()] < enemy[i]) {
                int t = pq.poll();
                sum -= enemy[t];
                pq.add(i);
                sum += enemy[i];
            }
            
            sum2 += enemy[i];
            if (sum2 - sum > n) {
                return i;
            }
        }
        
        return enemy.length;
    }
}