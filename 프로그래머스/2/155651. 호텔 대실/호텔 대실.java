import java.util.*;

class Solution {
    
    private static final String EXIT_EVENT_SUFFIX = "A";
    private static final String ENTER_EVENT_SUFFIX = "B";
    
    public int solution(String[][] book_time) {
        PriorityQueue<String> eventQueue = new PriorityQueue<>();
        int totalRoom = 0;
        int usingRoom = 0;
        
        for (int i = 0; i < book_time.length; i++) {
            // 입실 이벤트
            eventQueue.add((book_time[i][0]).concat(ENTER_EVENT_SUFFIX));
            
            // 퇴실 시간 + 청소 10분
            String[] s = book_time[i][1].split(":");
            int endHour = Integer.parseInt(s[0]);
            int endMinute = Integer.parseInt(s[1]) + 10;
            
            if (endMinute >= 60) {
                endMinute -= 60;
                endHour += 1;
            }
            
            String formattedEnd = String.format("%02d:%02d", endHour, endMinute);
            eventQueue.add(formattedEnd.concat(EXIT_EVENT_SUFFIX));
        }
        
        while (!eventQueue.isEmpty()) {
            String event = eventQueue.poll();
            // System.out.println(event); 
            if (event.charAt(event.length() - 1) == 'A') {
                usingRoom -= 1;
            } else {
                if (usingRoom == totalRoom) {
                    totalRoom += 1;
                }
                usingRoom += 1;
            }
        }
        
        return totalRoom;
    }

}
