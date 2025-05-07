class Solution {
    public String solution(String s) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            String addCh;
            if (s.charAt(i) == ' ') {
                addCh = " ";
                cnt = -1;
            } else if (cnt % 2 == 0) {
                addCh = String.valueOf(s.charAt(i)).toUpperCase();
            } else {
                addCh = String.valueOf(s.charAt(i)).toLowerCase();
            }
            cnt += 1;
            sb.append(addCh);
        }
        return sb.toString();
    }
}