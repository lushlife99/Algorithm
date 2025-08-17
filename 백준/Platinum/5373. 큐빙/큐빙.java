import java.io.*;

/**
 * Boj 5373
 * 구현, 시뮬
 */

public class Main {

    static char[][] up, down, front, back, left, right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] data = br.readLine().split(" ");
            initCube();
            for (String str : data) rotate(str.charAt(0), str.charAt(1));
            for (char[] row : up) sb.append(new String(row)).append("\n");
        }
        System.out.print(sb);
    }

    static void initCube() {
        up = fill('w'); down = fill('y'); front = fill('r'); back = fill('o'); left = fill('g'); right = fill('b');
    }

    static char[][] fill(char c) {
        char[][] arr = new char[3][3];
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) arr[i][j] = c;
        return arr;
    }

    static void rotate(char move, char dir) {
        switch (move) {
            case 'U': rotateU(dir); break;
            case 'D': rotateD(dir); break;
            case 'F': rotateF(dir); break;
            case 'B': rotateB(dir); break;
            case 'L': rotateL(dir); break;
            case 'R': rotateR(dir); break;
        }
    }

    static void rotateU(char d) {
        up = (d == '+') ? rot(up, true) : rot(up, false);
        if (d == '+') cycle(back[0], left[0], front[0], right[0]);
        else cycle(back[0], right[0], front[0], left[0]);
    }

    static void rotateD(char d) {
        down = (d == '+') ? rot(down, true) : rot(down, false);
        if (d == '+') cycle(back[2], right[2], front[2], left[2]);
        else cycle(back[2], left[2], front[2], right[2]);
    }

    static void rotateF(char d) {
        front = (d == '+') ? rot(front, true) : rot(front, false);
        if (d == '+') {
            char[] t = up[2].clone();
            for (int i = 0; i < 3; i++) up[2][i] = left[2 - i][2];
            for (int i = 0; i < 3; i++) left[i][2] = down[2][2 - i];
            for (int i = 0; i < 3; i++) down[2][i] = right[i][0];
            for (int i = 0; i < 3; i++) right[i][0] = t[i];
        } else {
            char[] t = up[2].clone();
            for (int i = 0; i < 3; i++) up[2][i] = right[i][0];
            for (int i = 0; i < 3; i++) right[i][0] = down[2][i];
            for (int i = 0; i < 3; i++) down[2][i] = left[2 - i][2];
            for (int i = 0; i < 3; i++) left[i][2] = t[2 - i];
        }
    }

    static void rotateB(char d) {
        back = (d == '+') ? rot(back, true) : rot(back, false);
        if (d == '+') {
            char[] t = up[0].clone();
            for (int i = 0; i < 3; i++) up[0][i] = right[i][2];
            for (int i = 0; i < 3; i++) right[i][2] = down[0][i];
            for (int i = 0; i < 3; i++) down[0][i] = left[2 - i][0];
            for (int i = 0; i < 3; i++) left[i][0] = t[2 - i];
        } else {
            char[] t = up[0].clone();
            for (int i = 0; i < 3; i++) up[0][i] = left[2 - i][0];
            for (int i = 0; i < 3; i++) left[i][0] = down[0][2 - i];
            for (int i = 0; i < 3; i++) down[0][i] = right[i][2];
            for (int i = 0; i < 3; i++) right[i][2] = t[i];
        }
    }

    static void rotateL(char d) {
        left = (d == '+') ? rot(left, true) : rot(left, false);
        if (d == '+') {
            char[] t = {up[0][0], up[1][0], up[2][0]};
            for (int i = 0; i < 3; i++) up[i][0] = back[2 - i][2];
            for (int i = 0; i < 3; i++) back[i][2] = down[i][2];
            for (int i = 0; i < 3; i++) down[i][2] = front[2 - i][0];
            for (int i = 0; i < 3; i++) front[i][0] = t[i];
        } else {
            char[] t = {up[0][0], up[1][0], up[2][0]};
            for (int i = 0; i < 3; i++) up[i][0] = front[i][0];
            for (int i = 0; i < 3; i++) front[i][0] = down[2 - i][2];
            for (int i = 0; i < 3; i++) down[i][2] = back[i][2];
            for (int i = 0; i < 3; i++) back[i][2] = t[2 - i];
        }
    }

    static void rotateR(char d) {
        right = (d == '+') ? rot(right, true) : rot(right, false);
        if (d == '+') {
            char[] t = {up[0][2], up[1][2], up[2][2]};
            for (int i = 0; i < 3; i++) up[i][2] = front[i][2];
            for (int i = 0; i < 3; i++) front[i][2] = down[2 - i][0];
            for (int i = 0; i < 3; i++) down[i][0] = back[i][0];
            for (int i = 0; i < 3; i++) back[i][0] = t[2 - i];
        } else {
            char[] t = {up[0][2], up[1][2], up[2][2]};
            for (int i = 0; i < 3; i++) up[i][2] = back[2 - i][0];
            for (int i = 0; i < 3; i++) back[i][0] = down[i][0];
            for (int i = 0; i < 3; i++) down[i][0] = front[2 - i][2];
            for (int i = 0; i < 3; i++) front[i][2] = t[i];
        }
    }

    static char[][] rot(char[][] a, boolean clockwise) {
        char[][] r = new char[3][3];
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++)
            r[i][j] = clockwise ? a[2 - j][i] : a[j][2 - i];
        return r;
    }

    static void cycle(char[] a, char[] b, char[] c, char[] d) {
        char[] t = a.clone();
        a = assign(a, b); b = assign(b, c); c = assign(c, d); d = assign(d, t);
    }

    static char[] assign(char[] to, char[] from) {
        for (int i = 0; i < 3; i++) to[i] = from[i];
        return to;
    }
}