import java.util.*;

class Solution {
    
    public static List<String> list = List.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    
    public int solution(String s) {
        for (int i = 0; i < 10; i++) {
            s = s.replaceAll(list.get(i), String.valueOf(i));
        }
        
        return Integer.parseInt(s);
    }
}