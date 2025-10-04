/*
dp
dp[n][4]

dp[n][k]

dp[n][0] = 1
dp[n][1] = dp[i-1][0] + dp[n-1][1] + dp[n-1][2]
dp[n][2] = dp[n-1][0~3]
dp[n][3] = dp[n-1][0~3]
*/

import java.util.*;
import java.util.stream.*;

class Solution {
    
    private int[][] dp;
    
    public int solution(int n, int[] tops) {
        
        dp = new int[n][4];
        
        
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][3] = 1;
        int sum = 3;
        if (tops[0] == 1) {
            dp[0][2] = 1;
            sum = 4;
        }
        
        
        for (int i = 1; i < n; i++) {
            if (tops[i] == 1) {
                dp[i][2] = sum;
            }
            
            dp[i][0] = sum;
            dp[i][1] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2];
            dp[i][3] = sum;
            
            sum = 0;
            for (int j = 0; j < 4; j++) {
                sum += dp[i][j];
                sum %= 10007;
            }
        }
        
        int answer = 0;
        for (int i = 0; i < 4; i++) {
            answer += dp[n-1][i];
        }
        
        return answer % 10007;
    }
}