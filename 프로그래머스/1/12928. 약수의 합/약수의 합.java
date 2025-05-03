class Solution {
    public long solution(int n) {
        long answer = 0L;
        
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                answer += i;
            }
        }
        return answer;
    }
}