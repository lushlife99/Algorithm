import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int k, int[] score) {
        ArrayList<Integer> answer = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < score.length; i++) {
            if (pq.size() < k) {
                pq.offer(score[i]);
            } else {
                if (pq.peek() < score[i]) {
                    pq.poll();
                    pq.offer(score[i]);
                }
            }
            
            answer.add(pq.peek());
        }
        
        
        return answer;
    }
}