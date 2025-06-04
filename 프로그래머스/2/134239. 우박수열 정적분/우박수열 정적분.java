import java.util.*;

class Solution {
    
    static ArrayList<Integer> arr = new ArrayList<>();
    static ArrayList<Double> sum = new ArrayList<>();
    
    public ArrayList<Double> solution(int k, int[][] ranges) {
        
        arr.add(k);
        
        while(k != 1) {
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = k * 3 + 1;
            }
            arr.add(k);
        }
        
        sum.add(0.0);
        
        for (int i = 1; i < arr.size(); i++) {
            double size = (double) (arr.get(i-1) + arr.get(i)) / 2;
            sum.add(sum.get(i-1) + size);
        }
        
        ArrayList<Double> answer = new ArrayList<>();
        
        for (int[] range : ranges) {
            int start = range[0];
            int end = arr.size() + range[1] - 1;
            if (start > end) {
                answer.add(-1.0);
                continue;
            }
            answer.add(sum.get(end) - sum.get(start));
        }
        return answer;
    }
}