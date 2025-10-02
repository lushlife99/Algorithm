import java.util.*;

class Solution {
    
    private Map<String, Gift> giftMap = new HashMap<>();
    private Map<String, Integer> cntMap = new HashMap<>();
    
    class Gift {
        int total = 0;
        Map<String, Integer> to = new HashMap<>();
        
        public void update(String sendTo) {
            this.total++;
            to.put(sendTo, to.getOrDefault(sendTo, 0) + 1);
        }
    }
    
    public int solution(String[] friends, String[] gifts) {
        
        for (int i = 0; i < friends.length; i++) {
            giftMap.put(friends[i], new Gift());
        }
        
        for (String g : gifts) {
            StringTokenizer st = new StringTokenizer(g);
            String from = st.nextToken();
            String to = st.nextToken();
            
            giftMap.get(from).update(to);
            giftMap.get(to).total--;
        }
        
        for (int i = 0; i < friends.length; i++) {
            String from = friends[i];
            for (int j = i+1; j < friends.length; j++) {
                String to = friends[j];
                int cnt1 = giftMap.get(from).to.getOrDefault(to, 0);
                int cnt2 = giftMap.get(to).to.getOrDefault(from, 0);
                
                if (cnt1 > cnt2) {
                    cntMap.put(from, cntMap.getOrDefault(from, 0) + 1);
                } else if (cnt1 < cnt2) {
                    cntMap.put(to ,cntMap.getOrDefault(to, 0) + 1);
                } else {
                    if (giftMap.get(from).total > giftMap.get(to).total) {
                        cntMap.put(from, cntMap.getOrDefault(from, 0) + 1);
                    } else if (giftMap.get(from).total < giftMap.get(to).total) {
                        cntMap.put(to ,cntMap.getOrDefault(to, 0) + 1);
                    }
                }
            }
        }
        
        int answer = cntMap.values().stream().max(Integer::compareTo).orElse(0);
        return answer;
    }
}