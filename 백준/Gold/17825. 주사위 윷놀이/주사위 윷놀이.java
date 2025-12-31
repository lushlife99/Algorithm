import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 17825 주사위 윷놀이
 * 시뮬, 구현
 */


public class Main {

    static class Node {
        int value;
        Node blue;
        Node red;
        boolean used;

        Node(int value) {
            this.value = value;
            this.used = false;
        }
    }

    static int[] dice = new int[10];
    static Node root;
    static Node[] horses = new Node[4];
    static int answer = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        root = initBoard();

        for (int i = 0; i < 4; i++) {
            horses[i] = root;
            root.used = true;
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    static int dfs(int depth, int score) {
        if (depth == 10) {
            answer = Math.max(answer, score);
            return score;
        }

        for (int i = 0; i < 4; i++) {
            Node cur = horses[i];
            if (cur == null) continue;

            Node next = move(cur, dice[depth]);
            if (next != null && next.used) continue;

            horses[i] = next;
            cur.used = false;
            if (next != null) next.used = true;

            dfs(depth + 1, score + (next == null ? 0 : next.value));

            horses[i] = cur;
            cur.used = true;
            if (next != null) next.used = false;
        }
        return score;
    }

    static Node move(Node start, int cnt) {
        Node cur = start;

        if (cur.blue != null) {
            cur = cur.blue;
            cnt--;
        } else {
            cur = cur.red;
            cnt--;
        }

        while (cnt > 0 && cur != null) {
            cur = cur.red;
            cnt--;
        }

        return cur;
    }
    static Node initBoard() {
        Node root = new Node(0);

        // 2 to 40
        Node prev = root;
        Node n10 = null;
        Node n20 = null;
        Node n30 = null;
        Node n40 = null;

        for (int v = 2; v <= 40; v+=2) {
            Node n = new Node(v);
            prev.red = n;
            prev = n;
            if (v == 10) {
                n10 = n;
            } else if (v == 20) {
                n20 = n;
            } else if (v == 30) {
                n30 = n;
            } else if (v == 40) {
                n40 = n;
            }
        }

        Node end = new Node(0);
        prev.red = end;

        // 10 to 25
        Node n13 = new Node(13);
        n10.blue = n13;

        Node n16 = new Node(16);
        n13.red = n16;

        Node n19 = new Node(19);
        n16.red = n19;

        Node n25 = new Node(25);
        n19.red = n25;

        // 20 to 25

        Node n22 = new Node(22);
        n20.blue = n22;

        Node n24 = new Node(24);
        n22.red = n24;

        n24.red = n25;

        //30 to 25

        Node n28 = new Node(28);
        n30.blue = n28;

        Node n27 = new Node(27);
        n28.red = n27;

        Node n26 = new Node(26);
        n27.red = n26;

        n26.red = n25;

        // 25 to 40
        Node n30_2 = new Node(30);
        n25.red = n30_2;

        Node n35 = new Node(35);
        n30_2.red = n35;

        n35.red = n40;

        return root;
    }
}
