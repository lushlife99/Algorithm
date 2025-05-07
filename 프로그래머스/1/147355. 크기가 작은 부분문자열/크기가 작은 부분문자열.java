class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        int start = 0;
        for (int end = p.length() - 1; end < t.length(); end++) {
            if (p.compareTo(t.substring(start, end+1)) >= 0) {
                answer += 1;
            }
            start++;
        }
        
        return answer;
    }
}