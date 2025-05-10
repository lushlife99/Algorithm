import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int N, int[] stages) {
        PriorityQueue<Integer> pq;
        float[] answer = new float[N];
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i = 1; i <= N; i++) {
            result.add(i);
        }
        
        for (int i = 0; i < N; i++) {
            int fail_cnt = 0;
            int cnt = 0;
            for (int stage : stages) {
                if (i+1 <= stage) {
                    cnt++;
                }
                
                if (i+1 == stage) {
                    fail_cnt++;
                }
            }
            
            if (cnt == 0) {
                answer[i] = 0;
            } else {
                answer[i] = (float)fail_cnt / cnt;
            }
        }
        
        Collections.sort(result, (i1, i2) -> {
            
            if (answer[i1-1] - answer[i2-1] > 0) {
                return -1;
            } else if (answer[i1-1] - answer[i2-1] == 0) {
                return 0;
            }
            return 1;
        });
        return result;
    }
}