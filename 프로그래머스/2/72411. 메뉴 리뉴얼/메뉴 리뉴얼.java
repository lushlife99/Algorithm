/**
Map에 저장
**/

import java.util.*;

class Solution {
    
    private Map<String, Integer> map = new HashMap<>();
    
    public ArrayList<String> solution(String[] orders, int[] course) {
        
        ArrayList<String> answer = new ArrayList<>();
        int[] maxCount = new int[11];
        for (String s : orders) {
            dfs(s, course, new boolean[s.length()], "", 0);
        }
        
        for (String key : map.keySet()) {
            int value = map.get(key);
            if (value > maxCount[key.length()]) {
                maxCount[key.length()] = value;
            }
        }
        
        for (int i = 0; i < course.length; i++) {
            int targetCount = maxCount[course[i]];
            if (targetCount < 2) {
                continue;
            }
            for (String key : map.keySet()) {
                if (key.length() == course[i] && map.get(key) == targetCount) {
                    answer.add(key);
                }
            }
        }
        
        Collections.sort(answer);
        
        return answer;
    }
    
    public void dfs(String order, int[] course, boolean[] visited, String s, int idx) {
        for (int i = 0; i < course.length; i++) {
            if (course[i] == s.length()) {
                if (map.containsKey(s)) {
                    map.put(s, map.get(s) + 1);    
                } else {
                    map.put(s, 1);
                }
                
            }
        }
        
        for (int i = idx; i < order.length(); i++) {
            
            if (!visited[i]) {
                if (s.indexOf((int)order.charAt(i)) == -1) {
                    visited[i] = true;
                    char[] charArray = s.concat(String.valueOf(order.charAt(i))).toCharArray();
                    Arrays.sort(charArray);
                    dfs(order, course, visited, new String(charArray), i+1);
                    visited[i] = false;
                }
            }
           
        }
        
    } 
}