class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        
        
        for (int i = 1; i < food.length; i++) {
            sb.append(String.valueOf(i).repeat(food[i]/2));
        }
        
        StringBuilder sb2 = new StringBuilder(sb.toString());
        
        sb.append("0");
        sb.append(sb2.reverse().toString());
        
        return sb.toString();
    }
}