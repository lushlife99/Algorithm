import java.io.*;
import java.util.*;


/**
 * boj 2250 트리의 높이와 너비
 */


public class Main {

    static class Node {
        int id;
        int left;
        int right;
        int x, y;

        public Node(int id, int left, int right) {
            this.id = id; this.left = left; this.right = right;
        }

        @Override
        public String toString() {
            return "id : " + this.id + ", x : " + x + ", y : " + y;
        }
    }

    private static int N;
    private static int col = 0;
    private static int maxLevel = 0;
    private static Map<Integer, Node> nodeMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] inDegree = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            nodeMap.put(id, new Node(id, left, right));
            if (left != -1) inDegree[left-1]++;
            if (right != -1) inDegree[right-1]++;
        }

        // 1. 루트 찾기
        int rootId = 0;

        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                rootId = i+1;
                break;
            }
        }

        // 2. 루트노드부터 x,y값 갱신
        build(rootId, 1);

        int[][] res = new int[maxLevel+1][2];
        for (int i = 1; i <= maxLevel; i++) {
            res[i][0] = Integer.MAX_VALUE;
            res[i][1] = Integer.MIN_VALUE;
        }

        for (Node n : nodeMap.values()) {
            res[n.x][0] = Math.min(res[n.x][0], n.y);
            res[n.x][1] = Math.max(res[n.x][1], n.y);
        }

        int level = 0;
        int size = 0;
        for (int i = 1; i <= maxLevel; i++) {
            int levelSize = res[i][1] - res[i][0] + 1;
            if (size < levelSize) {
                size = levelSize;
                level = i;
            }
        }

        System.out.printf(level + " " + size);
    }

    private static void build(int id, int level) {
        Node node = nodeMap.get(id);

        if (maxLevel < level) {
            maxLevel = level;
        }

        if (node.left != -1) {
            build(node.left, level+1);
        }

        node.x = level;
        node.y = ++col;

        if (node.right != -1) {
            build(node.right, level+1);
        }
    }

}