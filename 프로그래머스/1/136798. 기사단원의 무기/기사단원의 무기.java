class Solution {
    public long solution(int number, int limit, int power) {
        long answer = 0;
        int[] arr = new int[number+1];
        for (int i = 1; i <= number; i++) {
            for (int j = i; j <= number; j += i) {
                arr[j] += 1;
            }
        }
        
        
        for (int i = 1; i <= number; i++) {
            if (arr[i] > limit) {
                answer += power;
            } else {
                answer += arr[i];
            }
        }
        
        return answer;
    }
}