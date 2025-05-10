/**

**/

import java.util.*;

class Solution {
    
    private final String[] speakAble = {"aya", "ye", "woo", "ma"};
    private final Set<String> set = new HashSet<>();
    
    public int solution(String[] babbling) {
        
        int answer = 0;
        
        for (String s : babbling) {
            boolean signal = true;
            for (String l : speakAble) {
                if (s.indexOf(l.repeat(2)) != -1) {
                    System.out.println(l.repeat(2));
                    signal = false;
                    break;
                }
            }
            
            if (signal) {
                for (String l : speakAble) {
                    s = s.replace(l, " ");
                }
                
                if (s.trim().length() == 0) {
                    answer += 1;
                }
            }
        }
        return answer;
    }

}