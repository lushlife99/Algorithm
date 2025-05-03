import java.util.*;
import java.lang.*;

class Solution {
    public long solution(long n) {
        ArrayList<Character> array = new ArrayList<>();
        String str = String.valueOf(n);
        for (int i = 0; i < str.length(); i++) {
            array.add(str.charAt(i));
        }
        Collections.sort(array);
        Collections.reverse(array);
        
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            st.append(array.get(i));
        }
        
        return Long.parseLong(st.toString());
    }
}