class Solution {
    
    public String[] DAY_NAME = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
    public int[] days = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public String solution(int a, int b) {
        int currentDayIdx = 5; // 1월 1일은 금요일
        
        for (int i = 0; i < a-1; i++) {
            currentDayIdx = (currentDayIdx + days[i]) % 7;
        }
        
        return DAY_NAME[(currentDayIdx+b-1) % 7];
    }
}