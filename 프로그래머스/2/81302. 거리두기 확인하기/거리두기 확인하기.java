import java.util.*;

class Solution {
    public ArrayList<Integer> solution(String[][] places) {
        ArrayList<Integer> answer = new ArrayList<>();
        int[][] arround1 = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        int cnt = 0;
        for (String[] place : places) {
            cnt++;
            char[][] chars = new char[5][5];
            for (int i = 0; i < place.length; i++) {
                chars[i] = place[i].toCharArray();
            }
            
            boolean sig = false;
            
            for (int cx = 0; cx < 5; cx++) {
                for (int cy = 0; cy < 5; cy++) {
                    
                    if (chars[cx][cy] != 'P') continue;
                    
                    Queue<Integer> q = new LinkedList<>();
                    
                    for (int i = 0; i < arround1.length; i++) {
                        int nx = cx + arround1[i][0];
                        int ny = cy + arround1[i][1];
                    
                        if (0 <= nx && nx < 5 && 0 <= ny && ny < 5) {
                            if (chars[nx][ny] == 'P') {
                                answer.add(0);
                                sig = true;
                                break;
                            }
                            
                            if (chars[nx][ny] == 'O') {
                                q.add(i);
                            }
                        }
                    }
                    
                    if (sig) {
                        break;
                    }
                    
                    while (!q.isEmpty()) {
                        int arroundIdx = q.poll();
                        
                        for (int i = 0; i < arround1.length; i++) {
                            int nx = cx + arround1[arroundIdx][0] + arround1[i][0];
                            int ny = cy + arround1[arroundIdx][1] + arround1[i][1];
                            
                            if (nx == cx && ny == cy) {
                                continue;
                            }
                            
                            if (0 <= nx && nx < 5 && 0 <= ny && ny < 5) {
                                if (chars[nx][ny] == 'P') {
                                    answer.add(0);
                                    sig = true;
                                    break;
                                }
                            }
                        }
                        
                        if (sig) {
                            break;
                        }
                    }
                    
                    if (sig) {
                        break;
                    }
                }
                
                if (sig) {
                    break;
                }
            }
            
            if (!sig) {
                answer.add(1);
            }
          
        }
        
        return answer;
    }
}