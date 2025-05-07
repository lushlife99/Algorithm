class Solution {
    public String solution(String s, int n) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == ' ') {
                sb.append(' ');
            } else if ('a' <= c && c <= 'z') {
                sb.append((char) (((c - 'a' + n) % 26) + 'a'));
            } else if ('A' <= c && c <= 'Z') {
                sb.append((char) (((c - 'A' + n) % 26) + 'A'));
            }
        }
        return sb.toString();
    }
}