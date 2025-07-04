import java.util.*;

class Solution {
    public List<Long> solution(long begin, long end) {
        List<Long> answer = new ArrayList<>();
        
        for (long num = begin; num <= end; num++) {
            answer.add(getMaxDivisor(num));
        }
        
        return answer;
    }

    private long getMaxDivisor(long n) {
        if (n == 1) return 0;
        long divisor = 1L;

        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                divisor = i;
                long pair = n / i;
                if (pair <= 10_000_000) return pair;
            }
        }

        return divisor;
    }
}
