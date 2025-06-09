
class Solution {
    public long solution(int w, int h) {
        long answer = (long)w * h;
        int cnt = 0;
        double prevRes = h;
        
        for (int i = 1; i <= w; i++) {
            double res = -(double)h / w * i + h;
            cnt += ((int) prevRes - (int)res);
            
            if (res - (int)(res / 1) == 0.0) {
                if ((long)(h - res) * w ==  (long)i * h) {
                    cnt = cnt * (w / i);
                    break;
                }
                prevRes = res;
            } else {
                prevRes = (double)((int)res / 1) + 1;
            }
        }
        return answer - cnt;
    }
}