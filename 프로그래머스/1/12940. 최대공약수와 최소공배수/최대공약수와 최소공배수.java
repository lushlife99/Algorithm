class Solution {
    public long[] solution(int n, int m) {
        long[] answer = new long[2];
        answer[0] = getGCD(Math.max(n, m), Math.min(n, m));
        answer[1] = getLCM(Math.max(n, m), Math.min(n, m));
        return answer;
    }
    
    public int getGCD(int a, int b) {
        
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
    
    public long getLCM(int a, int b) {
        int gcd = getGCD(a, b);
        return a * b / gcd;
    }
}