import java.util.*;
class Solution {
    String[] pri = {"*+-","*-+","+-*","+*-","-*+","-+*"}; // 연산자 우선순위
    public long solution(String exp) {
        List<Long> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();

        for(int i = 0; i < exp.length(); ){ // String exp를 long , char로
            int j = i;
            while(j < exp.length() && Character.isDigit(exp.charAt(j))) j ++;
            if(j != exp.length()) ops.add(exp.charAt(j)); 
            nums.add(Long.valueOf(exp.substring(i,j)));
            i = j + 1;
        }

        long max = 0;
        for(String p : pri){ //연산자 우선순위별로 iter
            List<Long>numsC = new ArrayList<>(nums);
            List<Character>opsC = new ArrayList<>(ops);

            for(char c : p.toCharArray()){ 
                for(int i = 0; i < opsC.size();){
                    char op = opsC.get(i);
                    if(op != c) {i++; continue;}
                    if(op == '*') numsC.set(i, numsC.get(i) * numsC.get(i + 1));    
                    else if(op == '-')numsC.set(i, numsC.get(i) - numsC.get(i + 1)) ;
                    else if(op == '+')numsC.set(i, numsC.get(i) + numsC.get(i + 1)) ;

                    numsC.remove(i + 1);
                    opsC.remove(i);
                }
            } 

            max = Math.max(max, Math.abs(numsC.get(0)));
        }

        return max;
    }
}