class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int min = Integer.MAX_VALUE;
        int a = 0;
        
        for (int i = 0; i < arrayA.length; i++) {
            if (arrayA[i] < min) {
                min = arrayA[i];
            }
        }
        
        
        for (int j = 2; j <= min; j++) {
            boolean signal = true;
            for (int i = 0; i < arrayA.length; i++) {        
                if (arrayA[i] % j != 0) {
                    signal = false;
                    break;
                }
            }
            
            if (signal) {
                for (int i = 0; i < arrayB.length; i++) {
                    if (arrayB[i] % j == 0) {
                        signal = false;
                        break;
                    }
                }
            }
            
            if (signal) {
                a = Math.max(a, j);
            }
        }
        
        min = Integer.MAX_VALUE;
        for (int i = 0; i < arrayB.length; i++) {
            if (arrayB[i] < min) {
                min = arrayB[i];
            }
        }
        
        
        for (int j = 2; j <= min; j++) {
            boolean signal = true;
            for (int i = 0; i < arrayB.length; i++) {        
                if (arrayB[i] % j != 0) {
                    signal = false;
                    break;
                }
            }
            
            if (signal) {
                for (int i = 0; i < arrayA.length; i++) {
                    if (arrayA[i] % j == 0) {
                        signal = false;
                        break;
                    }
                }
            }
            
            if (signal) {
                a = Math.max(a, j);
            }
        }
        
        return a;
    }
}