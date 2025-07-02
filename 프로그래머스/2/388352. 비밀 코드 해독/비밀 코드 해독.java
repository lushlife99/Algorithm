/*
완탐
*/
import java.util.*;


class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        List<List<Integer>> res = dfs(n, new ArrayList<>());
        
        for (List<Integer> comb : res) {
            if (validate(comb, q, ans)) answer++;
            
                                         
        }
        return answer;
    }
    
    public List<List<Integer>> dfs(int n, List<Integer> arr) {
        List<List<Integer>> res = new ArrayList<>();
        if (arr.size() == 5) {
            res.add(new ArrayList<>(arr));
            return res;
        }
        int start = 0;
        if (!arr.isEmpty()) start = arr.get(arr.size()-1);
        
        for (int i = start+1; i <= n; i++) {
            arr.add(i);
            res.addAll(dfs(n, arr));
            arr.remove(arr.size()-1);
        }
        return res;
    }
    
    public boolean validate(List<Integer> arr, int[][] q, int[] ans) {
        
        for (int i = 0; i < q.length; i++) {
            Set<Integer> set = new HashSet<>(arr);
            int duplicatedCnt = 0;
            for (int j = 0; j < q[i].length; j++) {
                if (set.contains(q[i][j])) duplicatedCnt++;
            }
            
            if (duplicatedCnt != ans[i]) return false;
        }
        
        return true;
    }
}