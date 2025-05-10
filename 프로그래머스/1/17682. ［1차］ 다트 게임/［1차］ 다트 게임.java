import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int[] answers = new int[3];
        ArrayList<String> arr = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < dartResult.length(); i++) {
            if (dartResult.charAt(i) == '*' || dartResult.charAt(i) == '#') {
                sb.append(dartResult.charAt(i));
                arr.add(sb.toString());
                sb = new StringBuilder();
            }
            
            else if (Character.isDigit(dartResult.charAt(i))) {
                String s = sb.toString();
                if (s.contains("S")) {
                    arr.add(sb.toString());
                    sb = new StringBuilder();
                    sb.append(dartResult.charAt(i));
                } else if (s.contains("D")) {
                    arr.add(sb.toString());
                    sb = new StringBuilder();
                    sb.append(dartResult.charAt(i));
                } else if (s.contains("T")) {
                    arr.add(sb.toString());
                    sb = new StringBuilder();
                    sb.append(dartResult.charAt(i));
                } else {
                    sb.append(dartResult.charAt(i));
                }
            } else {
                sb.append(dartResult.charAt(i));
            }
        }
        
        if (sb.length() != 0) {
            arr.add(sb.toString());    
        }
        
        
        for (int i = 0; i < 3; i++) {
            String s = arr.get(i);
            
            if (s.contains("S")) {
                String[] res = s.split("S");
                answers[i] = Integer.parseInt(res[0]);
                
            } else if(s.contains("D")) {
                String[] res = s.split("D");
                answers[i] = (int)Math.pow(Integer.parseInt(res[0]), 2);
            } else {
                // Triple
                String[] res = s.split("T");
                answers[i] = (int)Math.pow(Integer.parseInt(res[0]), 3);
            }
            
            if (s.contains("*")) {
                answers[i] *= 2;
                    
                if (i != 0) {
                    answers[i-1] *=2;
                }
            }
                
            if(s.contains("#")) {
                answers[i] *= -1;
            }
            
        }
        
        return answers[0] + answers[1] + answers[2];
    }
}