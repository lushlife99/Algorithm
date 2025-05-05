import java.util.*;

class Solution {
    public ArrayList solution(int[] arr, int divisor) {
        ArrayList<Integer> answer = new ArrayList<>();
        Arrays.sort(arr);
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % divisor == 0) {
                answer.add(arr[i]);
            }
        }
        
        if (answer.isEmpty()) {
            answer.add(-1);
        }
        
        return answer;
    }
}