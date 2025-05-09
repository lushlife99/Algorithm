import java.util.*;

class Solution {
    
    public int[][] arr = {{1, 2, 3, 4, 5}, {2, 1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
    
    public ArrayList<Integer> solution(int[] answers) {
        int[] answerCount = new int[3];
        
        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (answers[i] == arr[j][i%arr[j].length]) {
                    answerCount[j] += 1;
                }
            }
        }
        
        int max = Arrays.stream(answerCount).max().getAsInt();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (answerCount[i] == max) {
                result.add(i+1);
            }
        }
        return result;
    }
}