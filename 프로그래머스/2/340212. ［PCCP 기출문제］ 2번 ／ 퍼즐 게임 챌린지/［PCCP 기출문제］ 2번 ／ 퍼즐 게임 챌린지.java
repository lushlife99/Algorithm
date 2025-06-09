class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int left = 1;
        int right = 100000;
        
        while (left < right) {
            int mid = (left + right) / 2;
            
            long solutionTime = getSolutionTime(diffs, times, mid, limit);
            if (solutionTime <= limit) {
                right = mid;
            } else {
                left = mid + 1;
            }   
        }
        
        
        return left;
    }
    
    public long getSolutionTime(int[] diffs, int[] times, int level, long limit) {
        long res = 0L;
        for (int i = 0; i < diffs.length; i++) {
            if (res > limit) {
                return res;
            }
            int currentLevel = diffs[i];
            
            if (currentLevel <= level) {
                res += times[i];
                continue;
            }
            
            res += (currentLevel - level) * (times[i-1] + times[i]) + times[i];
        }
        return res;
    }
}