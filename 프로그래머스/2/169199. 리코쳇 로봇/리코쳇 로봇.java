import java.util.*;

class Solution {
    public int solution(String[] board) {
        Queue<Point> q = new LinkedList<>();
        int[][] distance = new int[board.length][board[0].length()];
        Point start = null;
        Point goal = null;
        
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                distance[i][j] = Integer.MAX_VALUE;
                
                if (board[i].charAt(j) == 'R') {
                    start = new Point(i, j);
                }
                
                else if (board[i].charAt(j) == 'G') {
                    goal = new Point(i, j);
                }
            }
        }
        
        distance[start.x][start.y] = 0;
        q.add(start);
        
        while(!q.isEmpty()) {
            Point current = q.poll();
            if (current.x != 0) {
                for (int i = current.x; i > 0; i--) {
                    if (board[i-1].charAt(current.y) == 'D') {
                        if (distance[i][current.y] > distance[current.x][current.y] + 1) {
                            distance[i][current.y] = distance[current.x][current.y] + 1;
                            q.add(new Point(i, current.y));
                        }
                        break;
                    }
                    
                    if (i == 1) {
                        if (distance[0][current.y] > distance[current.x][current.y] + 1) {
                            distance[0][current.y] = distance[current.x][current.y] + 1;
                            q.add(new Point(0, current.y));
                        }
                        break;
                    }
                }
            }
            
            if (current.x != board.length - 1) {
                for (int i = current.x; i < board.length-1; i++) {
                    if (board[i+1].charAt(current.y) == 'D') {
                        if (distance[i][current.y] > distance[current.x][current.y] + 1) {
                            distance[i][current.y] = distance[current.x][current.y] + 1;
                            q.add(new Point(i, current.y));
                        }
                        break;
                    }
                    
                    if (i == board.length-2) {
                        if (distance[board.length-1][current.y] > distance[current.x][current.y] + 1) {
                            distance[board.length-1][current.y] = distance[current.x][current.y] + 1;
                            q.add(new Point(board.length-1, current.y));
                        }
                        break;
                        
                    }
                }
            }
            
            if (current.y != 0) {
                for (int i = current.y; i > 0; i--) {
                    if (board[current.x].charAt(i-1) == 'D') {
                        if (distance[current.x][i] > distance[current.x][current.y] + 1) {
                            distance[current.x][i] = distance[current.x][current.y] + 1;
                            q.add(new Point(current.x, i));
                        }
                        break;
                    }
                    
                    if (i == 1) {
                        if (distance[current.x][0] > distance[current.x][current.y] + 1) {
                            distance[current.x][0] = distance[current.x][current.y] + 1;
                            q.add(new Point(current.x, 0));
                        }
                        
                        break;
                    }
                }
            }
            
            if (current.y != board[current.x].length() - 1) {
                for (int i = current.y; i < board[current.x].length()-1; i++) {
                    if (board[current.x].charAt(i+1) == 'D') {
                        if (distance[current.x][i] > distance[current.x][current.y] + 1) {
                            distance[current.x][i] = distance[current.x][current.y] + 1;
                            q.add(new Point(current.x, i));
                        }
                        break;
                    }
                    
                    if (i == board[current.x].length()-2) {
                        if (distance[current.x][board[current.x].length()-1] > distance[current.x][current.y] + 1) {
                            distance[current.x][board[current.x].length()-1] = distance[current.x][current.y] + 1;
                            q.add(new Point(current.x, board[current.x].length()-1));
                            
                        }
                        break;
                    }
                }
            }
        }

        
        if (distance[goal.x][goal.y] == Integer.MAX_VALUE) {
            return -1;
        } 
        return distance[goal.x][goal.y];
    }
    
    class Point {
        int x;
        int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}