import java.util.*;

class Solution {
    
    private static final Map<Integer, Integer> prize = Map.ofEntries(
        Map.entry(6, 1),
        Map.entry(5, 2),
        Map.entry(4, 3),
        Map.entry(3, 4),
        Map.entry(2, 5)
    );
    
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        Set<Integer> set = new HashSet<>();
        
        int zeroCount = 0;
        int correctCount = 0;
        
        for (int i = 0; i < lottos.length; i++) {
            if (lottos[i] == 0) {
                zeroCount++;
            }
            
            set.add(lottos[i]);
        }
        
        for (int i = 0; i < win_nums.length; i++) {
            if (set.contains(win_nums[i])) {
                correctCount++;
            }
        }
        
        
        if (prize.containsKey(correctCount + zeroCount)) {
            answer[0] = prize.get(correctCount + zeroCount);
        } else {
            answer[0] = 6;
        }
        
        if (prize.containsKey(correctCount)) {
            answer[1] = prize.get(correctCount);
        } else {
            answer[1] = 6;
        }
        
        return answer;
        
    }
}