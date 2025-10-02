/*
n == 10 일때, 6^10 -> 시간초과
중간에서 만나기 -> 6^5
*/

import java.util.*;
import java.util.stream.*;

class Solution {
    
    private List<List<Integer>> playerComb;
    private List<List<Integer>> diceComb;
    
    public int[] solution(int[][] dice) {
        
        int N = dice.length;
        playerComb = combine(0, N, N/2, new ArrayList<>());
        diceComb = combine2(N/2, new ArrayList<>());
        List<Integer> res = new ArrayList<>();
        int max = 0;
        for (List<Integer> comb : playerComb) {
            int cnt = getWinCnt(comb, dice, N);
            if (cnt > max) {
                res = comb;
                max = cnt;
            }
        }
        
        int[] answer = res.stream()
            .mapToInt(i -> i+1)
            .toArray();
            
        return answer;
    }
    
    private int getWinCnt(List<Integer> comb, int[][] dice, int n) {
        Set<Integer> combSet = new HashSet<>(comb);
        List<Integer> otherComb = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!combSet.contains(i)) otherComb.add(i);
        }

        List<Integer> leftSum = new ArrayList<>();
        List<Integer> rightSum = new ArrayList<>();

        for (List<Integer> dComb : diceComb) {
            int sum = 0;
            for (int i = 0; i < comb.size(); i++) {
                sum += dice[comb.get(i)][dComb.get(i)];
            }
            leftSum.add(sum);

            sum = 0;
            for (int i = 0; i < otherComb.size(); i++) {
                sum += dice[otherComb.get(i)][dComb.get(i)];
            }
            rightSum.add(sum);
        }

        Collections.sort(rightSum);

        int res = 0;
        for (int lSum : leftSum) {
            int idx = upperBound(rightSum, lSum - 1);
            res += idx;
        }
        return res;
    }

    private int upperBound(List<Integer> list, int target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    
    private List<List<Integer>> combine2(int n, List<Integer> current) {
        List<List<Integer>> res = new ArrayList<>();
        if (current.size() == n) {
            res.add(new ArrayList<>(current));
            return res;
        }
        
        for (int i = 0; i < 6; i++) {
            current.add(i);
            res.addAll(combine2(n, current));
            current.remove(current.size()-1);
        }
        
        return res;
    }
    
    private List<List<Integer>> combine(int current, int end, int n, List<Integer> comb) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (comb.size() == n) {
            res.add(new ArrayList<>(comb));
            return res;
        }
        
        if (current < end) {
            comb.add(current);
            res.addAll(combine(current+1, end, n, comb));    
            comb.remove(comb.size()-1);
            
            res.addAll(combine(current+1, end, n, comb));
        }
        
        return res;
    }
}
