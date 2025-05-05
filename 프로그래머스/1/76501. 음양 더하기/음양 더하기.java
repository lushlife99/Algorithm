class Solution {
    public long solution(int[] absolutes, boolean[] signs) {
        long answer = 0;
        
        for (int i = 0; i < absolutes.length; i++) {
            if (signs[i]) {
                answer += absolutes[i];
            } else {
                answer -= absolutes[i];
            }
        }
        
        return answer;
    }
}