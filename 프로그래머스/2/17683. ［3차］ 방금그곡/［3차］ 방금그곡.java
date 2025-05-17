import java.time.*;
import java.util.*;
import java.time.format.DateTimeFormatter;

class Solution {
    public String solution(String m, String[] musicinfos) {
        List<Music> answers = new ArrayList<>();
        Music target = new Music(m);
        
        for (int index = 0; index < musicinfos.length; index++) {
            String musicinfo = musicinfos[index];
            String[] infos = musicinfo.split(",");
            LocalTime start = LocalTime.parse(infos[0], DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime end = LocalTime.parse(infos[1], DateTimeFormatter.ofPattern("HH:mm"));
            String title = infos[2];
            String melody = infos[3];
            
            Music music = new Music(start, end, title, melody, index);
            
            int totalMinute = getDuration(start, end);
            
            // 실제 재생된 악보 생성
            List<String> fullMelody = new ArrayList<>();
            for (int j = 0; j < totalMinute; j++) {
                fullMelody.add(music.akbo.get(j % music.akbo.size()));
            }
            
            // 대상 멜로디가 포함되는지 확인
            if (containsMelody(fullMelody, target.akbo)) {
                answers.add(music);
            }
        }
        
        if (answers.isEmpty()) {
            return "(None)";
        }
        
        // 재생 시간이 긴 순서대로 정렬, 같으면 먼저 입력된 순서
        Collections.sort(answers, (m1, m2) -> {
            int m1Duration = getDuration(m1.start, m1.end);
            int m2Duration = getDuration(m2.start, m2.end);
            if (m1Duration != m2Duration) {
                return Integer.compare(m2Duration, m1Duration); // 재생시간 내림차순
            }
            // 재생 시간이 같으면 먼저 입력된 음악 선택
            return Integer.compare(m1.inputOrder, m2.inputOrder);
        });
        
        return answers.get(0).name;
    }
    
    // 멜로디가 포함되는지 확인하는 함수
    private boolean containsMelody(List<String> fullMelody, List<String> targetMelody) {
        if (targetMelody.isEmpty() || targetMelody.size() > fullMelody.size()) return false;
        
        for (int i = 0; i <= fullMelody.size() - targetMelody.size(); i++) {
            boolean found = true;
            for (int j = 0; j < targetMelody.size(); j++) {
                if (!fullMelody.get(i + j).equals(targetMelody.get(j))) {
                    found = false;
                    break;
                }
            }
            if (found) return true;
        }
        return false;
    }
    
    // 시간 간격 계산 함수
    private int getDuration(LocalTime start, LocalTime end) {
        int startMinutes = start.getHour() * 60 + start.getMinute();
        int endMinutes = end.getHour() * 60 + end.getMinute();
        
        if (endMinutes < startMinutes) {
            // 자정을 넘어간 경우 (예: 23:50 ~ 00:10)
            endMinutes += 24 * 60;
        }
        
        return endMinutes - startMinutes;
    }
    
    class Music {
        LocalTime start;
        LocalTime end;
        String name;
        List<String> akbo;
        int inputOrder; // 입력 순서 저장
        
        public Music(String s) {
            this.akbo = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (i + 1 < s.length() && s.charAt(i + 1) == '#') {
                    akbo.add(s.substring(i, i + 2));
                    i++;
                } else {
                    akbo.add(String.valueOf(ch));
                }
            }
        }
        
        public Music(LocalTime start, LocalTime end, String name, String s) {
            this(s);
            this.start = start;
            this.end = end;
            this.name = name;
            this.inputOrder = 0;
        }
        
        public Music(LocalTime start, LocalTime end, String name, String s, int order) {
            this(s);
            this.start = start;
            this.end = end;
            this.name = name;
            this.inputOrder = order;
        }
    }
}