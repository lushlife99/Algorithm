import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> participantMap = new HashMap<>();
        
        for (String part : participant) {
            if (participantMap.containsKey(part)) {
                participantMap.put(part, participantMap.get(part) + 1);
            } else {
                participantMap.put(part, 1);
            }
        }
        
        for (String comp : completion) {
            int num = participantMap.get(comp);
            if (num == 1) {
                participantMap.remove(comp);
            } else {
                participantMap.put(comp, participantMap.get(comp) - 1);
            }
        }
        
        
        return (String)participantMap.keySet().toArray()[0];
    }
}