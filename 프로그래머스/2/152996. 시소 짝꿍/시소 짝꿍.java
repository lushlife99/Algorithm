//d
import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Map<Integer, Long> map = new HashMap<>();
        
        for (int i = 0; i < weights.length; i++) {
            if (map.containsKey(weights[i])) {
                map.put(weights[i], map.get(weights[i]) + 1);
            } else {
                map.put(weights[i], 1L);
            }
        }
        
        for (int weight : new TreeSet<>(map.keySet())) {
            int target_w = 0;
            if (map.get(weight) > 1) {
                answer += nCr(map.get(weight), 2);
            }
            
            if (map.containsKey(weight * 2)) {
                answer += map.get(weight) * map.get(weight*2);
            }
            
            if (weight % 2 == 0) {
                target_w = weight / 2 * 3;
                if (map.containsKey(target_w)) {
                    answer += map.get(weight) * map.get(target_w);
                }
            }
            
            if (weight % 3 == 0) {
                target_w = weight / 3 * 4;
                if (map.containsKey(target_w)) {
                    answer += map.get(weight) * map.get(target_w);
                }
            }
        }
     
        return answer;
    }
    
    public static long nCr(long n, long r) {
        if (r > n) return 0;
        if (r == 0 || r == n) return 1;

        r = Math.min(r, n - r);
        long result = 1;
        for (int i = 1; i <= r; i++) {
            result = result * (n - i + 1) / i;
        }
        return result;
    }
  
}