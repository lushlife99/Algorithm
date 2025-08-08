class Solution {
    private static int[] dx = {1, 0, 0, -1}; // d, l, r, u
    private static int[] dy = {0, -1, 1, 0};
    private static char[] dir = {'d', 'l', 'r', 'u'};

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder sb = new StringBuilder();

        int dist = Math.abs(x - r) + Math.abs(y - c);
        // 처음부터 불가능한 경우
        if (dist > k || (k - dist) % 2 != 0) return "impossible";

        int cx = x, cy = y;

        for (int step = 0; step < k; step++) {
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 1 || ny < 1 || nx > n || ny > m) continue;

                int remain = k - step - 1;
                int ndist = Math.abs(nx - r) + Math.abs(ny - c);
                if (ndist <= remain && (remain - ndist) % 2 == 0) {
                    sb.append(dir[i]);
                    cx = nx; cy = ny;
                    break;
                }
            }
        }
        return sb.toString();
    }
}
