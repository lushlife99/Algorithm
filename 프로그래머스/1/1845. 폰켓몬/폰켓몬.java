import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        Map<Integer, Boolean> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], true);
        }
        
        Set<Integer> keys = map.keySet();
        
        return Math.min(nums.length / 2, keys.size());
    }
}