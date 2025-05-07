import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int width = 0;
        int height = 0;
        
        for (int i = 0; i < sizes.length; i++) {
            Arrays.sort(sizes[i]);
            if (sizes[i][1] > width) {
                width = sizes[i][1];
            }
            
            if (sizes[i][0] > height) {
                height = sizes[i][0];
            }
        }
        return width * height;
    }
}