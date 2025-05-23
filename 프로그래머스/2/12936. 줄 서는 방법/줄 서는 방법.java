import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        long num = 1;
        List<Integer> list = new ArrayList<>();
        
        for(int i = 1 ; i<= n ; i++){
            list.add(i);  
            num*=i;
        } 
        
        int[] ans = new int[n];
        int idx = 0;
        k--;
        
        while (idx < n){
            num = num / (n - idx);
            ans[idx++] = list.remove((int)(k/num));
            k= k % num;
        }
        
        return ans;
    }
}