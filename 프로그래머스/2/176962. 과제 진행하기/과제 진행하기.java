import java.util.*;

class Solution {
    public List<String> solution(String[][] plans) {
        List<Submition> subList = new ArrayList<>();
        Stack<Submition> subStack = new Stack<>();
        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];
            int startTime = Integer.parseInt(plans[i][1].split(":")[0]) * 60 + Integer.parseInt(plans[i][1].split(":")[1]);
            int remainTime = Integer.parseInt(plans[i][2]);
            
            Submition sub = new Submition(name, startTime, remainTime);
            subList.add(sub);
        }
        
        Collections.sort(subList, (s1, s2) -> {
            return s1.startTime - s2.startTime;
        });

        
        List<String> answers = new ArrayList<>();
        
        for (int i = 0; i < subList.size(); i++) {
            Submition sub = subList.get(i);
            int endTime = sub.startTime + sub.remainTime;
            
            if (i == subList.size()-1) {
                answers.add(sub.name);
                
                while (!subStack.isEmpty()) {
                    answers.add(subStack.pop().name);
                }
            } else {
                int nextStartTime = subList.get(i+1).startTime;
                
                if (endTime < nextStartTime) {
                    answers.add(sub.name);
                    while (!subStack.isEmpty()) {
                        sub = subStack.peek();
                        if (endTime + sub.remainTime < nextStartTime) {
                            subStack.pop();
                            answers.add(sub.name);
                            endTime += sub.remainTime;
                        } else if (endTime + sub.remainTime == nextStartTime) {
                            subStack.pop();
                            answers.add(sub.name);
                            break;
                        } else {
                            sub.reduceRemainTime(nextStartTime - endTime);
                            break;
                        }
                    }
                } else if (endTime == nextStartTime){
                    answers.add(sub.name);
                } else {
                    sub.reduceRemainTime(nextStartTime - sub.startTime);
                    subStack.push(sub);
                }
            }
            
        }
        
        
        return answers;
    }
    
    class Submition {
        String name;
        int startTime;
        int remainTime;
        
        public Submition(String name, int startTime, int remainTime) {
            this.name = name;
            this.startTime = startTime;
            this.remainTime = remainTime;
        }
        
        public void reduceRemainTime(int time) {
            this.remainTime -= time;
            if (remainTime < 0) {
                remainTime = 0;
            }
        }
        
        @Override
        public String toString() {
            return this.name + " " + startTime + " " + remainTime;
        }
    }
}