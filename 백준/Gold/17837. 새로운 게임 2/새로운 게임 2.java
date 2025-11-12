import java.io.*;
import java.util.*;


/**
 * boj 17837 새로운 게임 2
 * 시뮬레이션
 */

public class Main {

    static class Node {
        int id, x, y, dir;

        public Node(int id, int x, int y, int dir) {
            this.id = id; this.x = x; this.y = y; this.dir = dir;
        }

        @Override
        public String toString() {
            return String.valueOf(this.id);
        }
    }

    private static int N, K;
    private static int[][] arr;
    private static List<Node>[][] board;
    private static List<Node> nodes = new ArrayList<>();

    private static int[][] directions = {{0,1}, {0,-1}, {-1,0}, {1,0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        board = new List[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = new ArrayList<>();
            }
        }

        int id = 1;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            Node node = new Node(id++, x, y, dir);
            nodes.add(node);
            board[x][y].add(node);
        }

        int answer = 0;

        while(answer++ <= 1000) {

            for (Node n : nodes) {
                int nx = n.x + directions[n.dir][0];
                int ny = n.y + directions[n.dir][1];

                if (nx < 0 || ny < 0 || nx == N || ny == N) {
                    n.dir = reverseDir(n.dir);
                    nx = n.x + directions[n.dir][0];
                    ny = n.y + directions[n.dir][1];
                }

                if (arr[nx][ny] == 0) { // white
                    List<Node> target = new ArrayList<>();

                    while(!board[n.x][n.y].isEmpty()) {
                        Node node = board[n.x][n.y].remove(board[n.x][n.y].size()-1);
                        target.add(0, node);

                        if (node.id == n.id) break;
                    }

                    board[nx][ny].addAll(target);
                    for (Node v : target) {
                        v.x = nx;
                        v.y = ny;
                    }
                    if (board[nx][ny].size() >= 4) {
                        System.out.println(answer);
                        return;
                    }
                } else if (arr[nx][ny] == 1) { // red
                    List<Node> target = new ArrayList<>();

                    while(!board[n.x][n.y].isEmpty()) {
                        Node node = board[n.x][n.y].remove(board[n.x][n.y].size()-1);
                        target.add(0, node);

                        if (node.id == n.id) break;
                    }

                    Collections.reverse(target);
                    board[nx][ny].addAll(target);
                    for (Node v : target) {
                        v.x = nx;
                        v.y = ny;
                    }
                    if (board[nx][ny].size() >= 4) {
                        System.out.println(answer);
                        return;
                    }
                } else { // blue
                    n.dir = reverseDir(n.dir);
                    int rnx = n.x + directions[n.dir][0];
                    int rny = n.y + directions[n.dir][1];

                    if (rnx < 0 || rny < 0 || rnx == N || rny == N) {
                        n.dir = reverseDir(n.dir);
                        rnx = n.x + directions[n.dir][0];
                        rny = n.y + directions[n.dir][1];
                    }

                    if (arr[rnx][rny] != 2) {
                        List<Node> target = new ArrayList<>();

                        while(!board[n.x][n.y].isEmpty()) {
                            Node node = board[n.x][n.y].remove(board[n.x][n.y].size()-1);
                            target.add(0, node);

                            if (node.id == n.id) break;
                        }
                        if (arr[rnx][rny] == 1) {
                            Collections.reverse(target);
                        }
                        board[rnx][rny].addAll(target);
                        for (Node v : target) {
                            v.x = rnx;
                            v.y = rny;
                        }
                        if (board[rnx][rny].size() >= 4) {
                            System.out.println(answer);
                            return;
                        }
                    }
                }
            }

        }

        answer = answer > 1000 ? -1 : answer;
        System.out.println(answer);
    }

    private static int reverseDir(int dir) {
        if (dir == 0) return 1;
        if (dir == 1) return 0;
        if (dir == 2) return 3;
        return 2;
    }

}
