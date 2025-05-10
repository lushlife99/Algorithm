import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();
        Map<Character, Character> map = new HashMap<>();
        
        for (int i = 0; i < 26; i++) {
            char ch = (char)('a' + i);
            int cnt = 0;
            char nextCh = ch;
            
            
            while (cnt < index) {
                nextCh = (char)(nextCh + 1);
                if (nextCh > 'z') {
                    nextCh = (char)(nextCh - 26);
                }
                
                if (!skip.contains(String.valueOf(nextCh))) {
                    cnt++;
                }
            }
            map.put(ch, nextCh);
        }
        
        for (int i = 0; i < s.length(); i++) {
            sb.append(map.get(s.charAt(i)));
        }
        
        return sb.toString();
    }
}