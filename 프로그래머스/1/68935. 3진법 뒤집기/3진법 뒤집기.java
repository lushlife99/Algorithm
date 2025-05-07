class Solution {
    public long solution(int n) {
        long answer = 0;
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.insert(0, String.valueOf(n % 3));
            n = n / 3;
        }
        String num_3 = sb.toString();
        
        for (int i = num_3.length() - 1; i >= 0; i--) {
            answer += ((long) Math.pow(3, i) * Character.getNumericValue(num_3.charAt(i)));
        }
        
        return answer;
    }
}