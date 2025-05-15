import java.util.*;

class Solution {
    public String solution(String new_id) {
        String answer = "";
        char[] chars = new_id.toCharArray();
        StringBuilder sb = new StringBuilder();
        
        //1, 2
        for (char c : chars) {
            if (Character.isDigit(c) || c == '-' || c == '_' || c == '.' || Character.isAlphabetic(c)) {
                if (Character.isAlphabetic(c)) {
                    sb.append(Character.toLowerCase(c));
                }  else {
                    sb.append(c);
                }
            } 
        }
        String s = sb.toString();
        sb = new StringBuilder();
        
        // 3
        for (int i = 0; i < s.length()-1; i++) {
            if (s.charAt(i) == '.' && s.charAt(i+1) == '.') {
                continue;
            }
            
            sb.append(s.charAt(i));
        }
        if (s.charAt(s.length()-1) != '.') {
            sb.append(s.charAt(s.length()-1));
        }
        
        // 4
        s = sb.toString();
        if (s.length() > 0 && s.charAt(0) == '.') {
            sb.deleteCharAt(0);
        }
        
        if (s.length() > 0 && s.charAt(s.length()-1) == '.') {
            sb.deleteCharAt(s.length()-1);
        }
        
        // 5
        
        if (sb.length() == 0) {
            sb.append("a");
        }
        
        // 6
        
        if (sb.length() > 15) {
            sb = new StringBuilder(sb.substring(0, 15));
            if (sb.charAt(sb.length()-1) == '.') {
                sb.deleteCharAt(sb.length()-1);
            }
        }
        
        // 7
        
        while (sb.length() <= 2) {
            sb.append(sb.charAt(sb.length()-1));
        }
        
        
        return sb.toString();
    }
}