import java.io.*;
import java.util.*;

/**
 * boj 17143 낚시왕
 * 시뮬레이션
 */


public class Main {
    static int R, C, M;
    static Shark[][] map;
    static List<Shark> sharks;
    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 1, -1};

    static class Shark {
        int r, c, s, d, z;
        public Shark(int r, int c, int s, int d, int z) {
            this.r = r; this.c = c; this.s = s; this.d = d; this.z = z;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        M = sc.nextInt();
        map = new Shark[R+1][C+1];
        sharks = new ArrayList<>();

        for(int i=0; i<M; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            int s = sc.nextInt();
            int d = sc.nextInt();
            int z = sc.nextInt();
            Shark sh = new Shark(r, c, s, d, z);
            sharks.add(sh);
            map[r][c] = sh;
        }

        int answer = 0;
        for(int col=1; col<=C; col++) {
            for(int row=1; row<=R; row++) {
                if(map[row][col] != null) {
                    answer += map[row][col].z;
                    sharks.remove(map[row][col]);
                    map[row][col] = null;
                    break;
                }
            }

            Shark[][] newMap = new Shark[R+1][C+1];
            for(Shark sh : sharks) {
                int nr = sh.r;
                int nc = sh.c;
                int speed = sh.s;

                if(sh.d <= 2) {
                    speed %= (2*(R-1));
                } else {
                    speed %= (2*(C-1));
                }

                for(int move=0; move<speed; move++) {
                    int tr = nr + dr[sh.d];
                    int tc = nc + dc[sh.d];
                    if(tr < 1 || tr > R || tc < 1 || tc > C) {
                        if(sh.d == 1) sh.d = 2;
                        else if(sh.d == 2) sh.d = 1;
                        else if(sh.d == 3) sh.d = 4;
                        else if(sh.d == 4) sh.d = 3;
                        tr = nr + dr[sh.d];
                        tc = nc + dc[sh.d];
                    }
                    nr = tr;
                    nc = tc;
                }
                sh.r = nr;
                sh.c = nc;

                if(newMap[nr][nc] == null || newMap[nr][nc].z < sh.z) {
                    newMap[nr][nc] = sh;
                }
            }
            map = newMap;
            sharks = new ArrayList<>();
            for(int r=1; r<=R; r++) {
                for(int c=1; c<=C; c++) {
                    if(map[r][c] != null) {
                        sharks.add(map[r][c]);
                    }
                }
            }
        }
        System.out.println(answer);
    }
}

