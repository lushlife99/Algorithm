class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        boolean[] visited = new boolean[n+1];
        
        for (int i = 0; i < section.length; i++) {
            if (!visited[section[i]]) {
                answer += 1;
                for (int j = 0; j < m; j++) {
                    if (section[i] + j == n) {
                        return answer;
                    }
                    visited[section[i] + j] = true;
                }
            }
        }
        return answer;
    }
}