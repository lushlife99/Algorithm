class Solution {
    
    public static int[][] dx = {{0,1}, {1,0}, {-1,0}, {0,-1}};
    
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        for (int i = 0; i < 4; i++) {
            int cx = dx[i][0] + h;
            int cy = dx[i][1] + w;
            if (cx >= 0 && cx < board.length) {
                if (cy >= 0 && cy < board[0].length) {
                    if (board[cx][cy].equals(board[h][w])) {
                        answer += 1;
                    }
                }
            }
        }
        return answer;
    }
}