class Solution {
    public long solution(long k, int d) {
        long answer = 0;
        
        for (long i = 0; i * k <= d; i++) {
            long x = i * k;
            long maxY = (long) Math.sqrt((long) d * d - x * x);
            long maxJ = maxY / k;
            answer += maxJ + 1;
        }
        
        return answer;
    }
}