import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 14725 개미굴
 * 문자열
 */


import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static class Node {
        String value;
        Map<String, Node> children = new TreeMap<>();

        Node(String value) {
            this.value = value;
        }
    }

    private static Node root = new Node("");

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int depth = Integer.parseInt(st.nextToken());

            Node cur = root;
            for (int j = 0; j < depth; j++) {
                String value = st.nextToken();
                cur.children.putIfAbsent(value, new Node(value));
                cur = cur.children.get(value);
            }
        }

        dfs(root, 0);
    }

    private static void dfs(Node node, int depth) {
        for (String key : node.children.keySet()) {
            for (int i = 0; i < depth; i++) System.out.print("--");
            System.out.println(key);
            dfs(node.children.get(key), depth + 1);
        }
    }
}

