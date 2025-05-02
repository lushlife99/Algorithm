class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int yCount = 0;
        int pCount = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'y' || s.charAt(i) == 'Y') {
                yCount += 1;
            }
            
            else if (s.charAt(i) == 'p' || s.charAt(i) == 'P') {
                pCount += 1;
            }
        }
        
        if (pCount != yCount) {
            answer = false;
        }

        return answer;
    }
}