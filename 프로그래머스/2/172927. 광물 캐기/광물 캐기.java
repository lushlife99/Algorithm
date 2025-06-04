/*
minerals 배열을 곡괭이 수에 맞춰 자르기
minerals 배열을 5개씩 끊어서 돌 곡괭이로 캐는 피로도 내림차순 정렬

*/

import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int totalTools = Arrays.stream(picks).sum();
        
        ArrayList<Exhaustion> exhaustArr = new ArrayList<>();
        
        for (int i = 0; i < totalTools; i++) {
            int start = i * 5;
            int end = start + 4;
            int exhaustSum = 0;
            for (int j = 0; j < 5; j++) {
                if (start+j >= minerals.length) {
                    end = start + j - 1;
                    break;
                }
                
                String mineral = minerals[start+j];
                if (mineral.equals("diamond")) {
                    exhaustSum += 25;
                }
                
                else if (mineral.equals("iron")) {
                    exhaustSum += 5;
                } else {
                    exhaustSum += 1;
                }
            }
            
            if (start <= end) {
                exhaustArr.add(new Exhaustion(start, end, exhaustSum));
            }
        }
        
        Collections.sort(exhaustArr, (a, b) -> {
            return b.value - a.value;
        });
        
        int exIdx = 0;
        
        while (picks[0] > 0 && exIdx < exhaustArr.size()) {
            Exhaustion ex = exhaustArr.get(exIdx++);
            answer += (ex.endIdx - ex.startIdx + 1);
            picks[0]--;
        }
        
        while (picks[1] > 0 && exIdx < exhaustArr.size()) {
            Exhaustion ex = exhaustArr.get(exIdx++);
            
            for (int i = ex.startIdx; i <= ex.endIdx; i++) {
                if (minerals[i].equals("diamond")) {
                    answer += 5;
                } else {
                    answer += 1;
                }
            }
            picks[1]--;
        }
        
        while (picks[2] > 0 && exIdx < exhaustArr.size()) {
            Exhaustion ex = exhaustArr.get(exIdx++);
            
            for (int i = ex.startIdx; i <= ex.endIdx; i++) {
                if (minerals[i].equals("diamond")) {
                    answer += 25;
                } else if (minerals[i].equals("iron")) {
                    answer += 5;
                } else {
                    answer += 1;
                }
            }
            picks[2]--;
        }
        return answer;
    }
    
    class Exhaustion {
        int startIdx;
        int endIdx;
        int value;
        
        public Exhaustion(int start, int end, int value) {
            this.startIdx = start;
            this.endIdx = end;
            this.value = value;
        }
        
        @Override
        public String toString() {
            return "startIdx : " + startIdx + " endIdx : " + endIdx + " value : " + value + "\n"; 
        }
    }
}