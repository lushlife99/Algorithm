import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        char[] chars = s.toCharArray();
        
        if (s.length() == 1) {
            return 1;
        } 
        
        for(int compressionSize = 1; compressionSize <= chars.length / 2; compressionSize++) {
            StringBuilder sb = new StringBuilder();
            StringBuilder rb = new StringBuilder(); // result string builder
            String token = "";
            int compressionCount = 0;
            
            for (int i = 0; i < chars.length / compressionSize; i++) {
                for (int j = 0; j < compressionSize; j++) {
                    sb.append(chars[i*compressionSize + j]);
                }
                
                if (token.equals(sb.toString()) || token.equals("")) {
                    compressionCount++;
                    token = sb.toString();
                    sb = new StringBuilder();
                } else {
                    rb.append(compressString(compressionCount, token));
                    compressionCount = 1;
                    token = sb.toString();
                    sb = new StringBuilder();
                }
            }
            
            rb.append(compressString(compressionCount, token));
            
            for (int i = chars.length / compressionSize * compressionSize; i < chars.length; i++){ 
                rb.append(chars[i]);                
            }
            answer = Math.min(answer, rb.toString().length());
        }
        
        return answer;
    }
    
    public String compressString(int compressionCount, String s) {
        String appendString = "";
        if (compressionCount == 1) {
            appendString = s;
        } else {
            appendString = String.valueOf(compressionCount) + s;
        }
                    
        return appendString;
    }
}