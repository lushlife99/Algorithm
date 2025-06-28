/*
1,4 2,3 3,2 4,1 -> 합
1,1 2,2 3,3 4,4 -> 차
*/

import java.util.*;

class Solution {
    
    private final static Set<Integer> diag1Set = new HashSet<>();
    private final static Set<Integer> diag2Set = new HashSet<>();
    
    public int solution(int n) {
        int answer = dfs(0, n, new boolean[n]);
        return answer;
    }
    
    public int dfs(int current, int n, boolean[] visitedCol) {
        
        int res = 0;
        if (current == n) {
            return 1;
        }
        
        for (int i = 0; i < n; i++) {
            if (visitedCol[i]) continue;
            
            int diag1 = current - i;
            int diag2 = current + i;
            
            if (diag1Set.contains(diag1)) continue;
            if (diag2Set.contains(diag2)) continue;
            
            visitedCol[i] = true;
            diag1Set.add(diag1);
            diag2Set.add(diag2);
            
            res += dfs(current+1, n, visitedCol);
            visitedCol[i] = false;
            diag1Set.remove(diag1);
            diag2Set.remove(diag2);
            
        }
        
        return res;
    }
}