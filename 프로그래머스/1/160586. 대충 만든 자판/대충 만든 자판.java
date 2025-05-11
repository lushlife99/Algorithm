import java.util.*;

class Solution {
    public ArrayList<Integer> solution(String[] keymap, String[] targets) {
        
        Map<Character, Integer> map = new HashMap<>();
        ArrayList<Integer> answers = new ArrayList<>();
        for (int i = 0; i < keymap.length; i++) {
            for (int j = 0; j < keymap[i].length(); j++) {
                char ch = keymap[i].charAt(j);
                if (map.containsKey(ch)) {
                    map.put(ch, Math.min(map.get(ch), j+1));
                } else {
                    map.put(ch, j+1);
                }
            }
        }
        
        for (String target : targets) {
            int count = 0;
            for (int i = 0; i < target.length(); i++) {
                char ch = target.charAt(i);
                if (map.containsKey(ch)) {
                    count += map.get(ch);
                } else {
                    count = -1;
                    break;
                }
            }
            
            answers.add(count);
        }
        
        return answers;
    }
}