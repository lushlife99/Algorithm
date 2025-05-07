import java.util.*;

class Solution {
    public int[] solution(String s) {
        Map<Character, Integer> chMap = new HashMap<>();
        int[] answer = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (chMap.containsKey(s.charAt(i))) {
                answer[i] = i - chMap.get(s.charAt(i));
            } else {
                answer[i] = -1;
            }
            chMap.put(s.charAt(i), i);
        }
        
        return answer;
    }
}