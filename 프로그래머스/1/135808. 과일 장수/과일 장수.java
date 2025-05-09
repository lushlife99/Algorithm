import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        Arrays.sort(score);
        
        int idx = score.length - 1;
        for (int i = 0; i < score.length / m; i++) {
            idx = idx - m + 1;
            answer += score[idx] * m;
            idx -= 1;
        }
        return answer;
    }
}