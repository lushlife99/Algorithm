

class Solution {
    public boolean solution(int x) {
        String s = String.valueOf(x);
        long sum = 0;
        
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - '0';
            sum += num;
        }
        
        if (x % sum == 0) {
            return true;
        }
        return false;
    }
}