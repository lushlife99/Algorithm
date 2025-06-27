/*
N명의 사용자 M개의 이모티콘

1. 이모티콘 플러스 가입자 최대
2. 판매액 최대

할인률을 40에서 내려가면 될듯?
*/

import java.util.*;

class Solution {
    
    private static int[] SALE_RATES = {10, 20, 30, 40};
    private static Set<ArrayList<Integer>> emoSaleTable = new HashSet<>();
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {0, 0};
        dfs(emoticons.length, 0, new ArrayList<Integer>());
        
        for (ArrayList<Integer> emoSaleRate : emoSaleTable) {
            int totalPrice = 0;
            int totalUserJoin = 0;
            for (int i = 0; i < users.length; i++) {
                int userSaleRateLimit = users[i][0];
                int userJoinPriceLimit = users[i][1];
                int price = 0;
                for (int j = 0; j < emoSaleRate.size(); j++) {
                    if (emoSaleRate.get(j) >= userSaleRateLimit) {
                        price += emoticons[j] / 100 * (100 - emoSaleRate.get(j));
                    }
                }
                
                if (price >= userJoinPriceLimit) {
                    totalUserJoin++;
                }
                else {
                    totalPrice+=price;
                }
            }
            
            if (answer[0] < totalUserJoin) {
                answer[0] = totalUserJoin;
                answer[1] = totalPrice;
            } else if(answer[0] == totalUserJoin) {
                if (answer[1] < totalPrice) {
                    answer[1] = totalPrice;
                }
            }   
        }
        
        return answer;
    }
    
    public void dfs(int target, int current, ArrayList<Integer> saleTable) {
        
        if (target == current) {
            emoSaleTable.add(new ArrayList<Integer>(saleTable));
            return;
        }
        
        for (int i = 0; i < SALE_RATES.length; i++) {
            saleTable.add(SALE_RATES[i]);
            dfs(target, current+1, saleTable);
            saleTable.remove(saleTable.size()-1);
        }
    }
    
}