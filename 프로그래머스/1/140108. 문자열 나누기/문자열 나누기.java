class Solution {
    public int solution(String s) {
        int answer = 0;
        char ch = '0';
        int xCount = 0;
        int otherCount = 0;

        for (int i = 0 ; i < s.length(); i++) {
            if (xCount + otherCount == 0) {
                ch = s.charAt(i);
                xCount++;
                continue;
            }
            
            if (s.charAt(i) == ch) {
                xCount++;
            } else {
                otherCount++;
            }
            
            if (xCount == otherCount) {
                answer++;
                xCount = otherCount = 0;
            }
        }
        
        if (xCount + otherCount != 0) {
            answer++;
        }
        return answer;
    }
}