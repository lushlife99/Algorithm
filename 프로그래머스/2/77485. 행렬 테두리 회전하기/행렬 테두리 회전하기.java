import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int rows, int columns, int[][] queries) {
        ArrayList<Integer> answers = new ArrayList<>();
        int[][] arr = new int[rows][columns];
        int num = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = num++;
            }
        }
        
        for (int[] query : queries) {
            int x1 = query[0] - 1;
            int y1 = query[1] - 1;
            int x2 = query[2] - 1;
            int y2 = query[3] - 1;
            int temp1 = 0;
            int temp2 = 0;
            int min = Integer.MAX_VALUE;
            
            //x1y1 -> x1y2
            temp1 = arr[x1][y2];
            for (int i = y2; i > y1; i--) {
                arr[x1][i] = arr[x1][i-1];
                min = Math.min(min, arr[x1][i]);
            }
            
            //x1y2 -> x2y2
            temp2 = temp1;
            temp1 = arr[x2][y2];
            for (int i = x2; i > x1; i--) {
                arr[i][y2] = arr[i-1][y2];
                min = Math.min(min, arr[i][y2]);
            }
            arr[x1+1][y2] = temp2;
            min = Math.min(min, temp2);
            
            //x2y2 -> x2y1
            temp2 = temp1;
            temp1 = arr[x2][y1];
            for (int i = y1; i < y2; i++) {
                arr[x2][i] = arr[x2][i+1];
                min = Math.min(min, arr[x2][i]);
            }
            arr[x2][y2-1] = temp2;
            min = Math.min(min, temp2);
            
            //x2y1 -> x1y1
            temp2 = temp1;
            for (int i = x1; i < x2; i++) {
                arr[i][y1] = arr[i+1][y1];
                min = Math.min(min, arr[i][y1]);
            }
            arr[x2-1][y1] = temp2;
            min = Math.min(min, temp2);
            answers.add(min);
        }
        return answers;
    }
}
