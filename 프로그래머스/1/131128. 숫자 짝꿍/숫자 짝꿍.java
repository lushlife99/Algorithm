/**
 실전처럼 풂.
 라이브러리 모르는거 검색 X
**/

import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        Map<Integer, Integer> xMap = new HashMap<>();
        Map<Integer, Integer> yMap = new HashMap<>();

        for (int i = 0; i < X.length(); i++) {
            int key = Integer.parseInt(String.valueOf(X.charAt(i)));
            if (xMap.containsKey(key)) {
                xMap.put(key, xMap.get(key)+1);
            }
            else {
                xMap.put(key, 1);
            }
        }

        for (int i = 0; i < Y.length(); i++) {
            int key = Integer.parseInt(String.valueOf(Y.charAt(i)));
            if (yMap.containsKey(key)) {
                yMap.put(key, yMap.get(key)+1);
            }
            else {
                yMap.put(key, 1);
            }
        }
        
        List<Integer> removeKey = new ArrayList<>();

        for (int key : xMap.keySet()) {
            if (yMap.containsKey(key)) {
                xMap.put(key, Math.min(yMap.get(key), xMap.get(key)));
            } else {
                removeKey.add(key);
            }
        }
        
        for (int k : removeKey) {
            xMap.remove(k);
        }

        StringBuilder sb = new StringBuilder();

        for (int key : xMap.keySet()) {
            String s = String.valueOf(key).repeat(xMap.get(key));
            sb.append(s);
        }
        
        

        char[] arr = sb.toString().toCharArray();
        Arrays.sort(arr);
        sb = new StringBuilder();
        
        for (int i = arr.length-1; i >= 0; i--) {
            sb.append(arr[i]);
        }
        String answer = sb.toString();
        
        if (answer.length() == 0) {
            return "-1";
        }
        int cnt = 0;
        
        for(int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == '0') {
                cnt++;
            }
        }
        
        if (cnt == answer.length()) {
            return "0";
        }
    
        return sb.toString();
    }
}