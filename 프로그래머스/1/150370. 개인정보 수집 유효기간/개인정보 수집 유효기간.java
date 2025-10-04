import java.util.*;

class Solution {
    
    private Map<String, Integer> termsMap = new HashMap<>();
    
    public List<Integer> solution(String today, String[] terms, String[] privacies) {
        
        for (String term : terms) {
            StringTokenizer st = new StringTokenizer(term);
            
            String key = st.nextToken();
            int value = Integer.parseInt(st.nextToken());
            termsMap.put(key, value);
        }
        
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            
            String privacy = privacies[i];
            StringTokenizer st = new StringTokenizer(privacy);
            String date = st.nextToken();
            String term = st.nextToken();
         
            if (isExpirated(date, today, termsMap.get(term))) answer.add(i+1);
        }
        
        return answer;
    }
    
    private boolean isExpirated(String target, String today, int ttl) {
        StringTokenizer st = new StringTokenizer(target, ".");
        
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
    
        if (ttl >= 12) {
            year += ttl / 12;
            ttl %= 12;
        }
        
        month += ttl;
        if (month > 12) {
            year++;
            month -= 12;
        }
        
        st = new StringTokenizer(today, ".");
        
        int tYear = Integer.parseInt(st.nextToken());
        int tMonth = Integer.parseInt(st.nextToken());
        int tDay = Integer.parseInt(st.nextToken());
        
        if (tYear > year) return true;
        else if (tYear == year) {
            if (tMonth > month) return true;
            else if (tMonth == month) {
                if (tDay >= day) {
                    return true;
                }
            }
        }
        
        return false;
    }
}