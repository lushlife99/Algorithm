class Solution {
    public long solution(int left, int right) {
        long answer = 0;
        
        for (int i = left; i <= right; i++) {
            int cnt = 1;
            for (int j = 1; j < i/2 + 1; j++) {
                if (i % j == 0) {
                    cnt += 1;
                }
            }
            
            if (cnt % 2 == 0) {
                answer += i;
            } else {
                answer -= i;
            }
        }
        return answer;
    }
}