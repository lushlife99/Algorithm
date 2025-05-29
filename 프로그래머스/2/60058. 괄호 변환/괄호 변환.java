import java.util.*;

class Solution {
    public String solution(String p) {
        
        return convert(p);
    }
    
    public String convert(String p) {
        
        if (p.length() == 0) {
            return "";
        }
        
        int cnt1 = 0;
        int cnt2 = 0;
        String u = p;
        String v = "";
        
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                cnt1++;
            }

            else {
                cnt2++;
            }

            if (cnt1 == cnt2) {
                u = p.substring(0, i+1);
                v = p.substring(i+1, p.length());
                break;
            }
        }

        if (isCorrect(u)) {
            return u + convert(v);
        } else {
            u = u.substring(1, u.length()-1);
            StringBuilder sb  = new StringBuilder("(" + convert(v) + ")");
            
            for (int j = 0; j < u.length(); j++) {
                if (u.charAt(j) == '(') {
                    sb.append(")");
                } else {
                    sb.append("(");
                }
            }
            return sb.toString();
        }
    }
    
    public boolean isCorrect(String s) {
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stk.add(s.charAt(i));
            }
            
            else {
                if (!stk.isEmpty()) {
                    stk.pop();
                } else {
                    return false;
                }
            }
        }
        
        if (stk.isEmpty()) {
            return true;
        }
        return false;
    }
}