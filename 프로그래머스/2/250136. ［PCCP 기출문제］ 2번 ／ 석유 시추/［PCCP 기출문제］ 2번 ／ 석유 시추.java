import java.util.*;

class Solution {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public int solution(int[][] land) {
        int row = land.length;
        int col = land[0].length;
        int[][] groupMap = new int[row][col];
        Map<Integer, Integer> groupSizeMap = new HashMap<>();
        int groupId = 1;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (land[i][j] == 1 && groupMap[i][j] == 0) {
                    
                    int size = bfs(i, j, land, groupMap, groupId);
                    groupSizeMap.put(groupId, size);
                    groupId++;
                }
            }
        }

        int answer = 0;
        for (int j = 0; j < col; j++) {
            Set<Integer> visitedGroups = new HashSet<>();
            int sum = 0;
            for (int i = 0; i < row; i++) {
                int gid = groupMap[i][j];
                if (gid > 0 && visitedGroups.add(gid)) {
                    sum += groupSizeMap.get(gid);
                }
            }
            answer = Math.max(answer, sum);
        }

        return answer;
    }

    private int bfs(int x, int y, int[][] land, int[][] groupMap, int groupId) {
        int count = 1;
        int row = land.length;
        int col = land[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        groupMap[x][y] = groupId;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (nx >= 0 && ny >= 0 && nx < row && ny < col) {
                    if (land[nx][ny] == 1 && groupMap[nx][ny] == 0) {
                        groupMap[nx][ny] = groupId;
                        queue.offer(new int[]{nx, ny});
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
