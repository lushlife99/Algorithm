import java.io.*;
import java.util.*;

/**
 * boj 2263 트리의 순회
 * 중위 순회 -> left, root, right
 * 후위 순회 -> left, right, root
 *
 * 후위 순회의 마지막은 항상 루트
 * 중위 순회에서 루트를 기준으로 left, right
 *
 */

public class Main {
    static int N;
    static int[] inorder, postorder;
    static int[] indexMap;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        inorder = new int[N];
        postorder = new int[N];
        indexMap = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            indexMap[inorder[i]] = i;
        }

        buildPreorder(0, N - 1, 0, N - 1);
        System.out.println(sb);
    }

    static void buildPreorder(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) return;

        int root = postorder[postEnd];
        sb.append(root).append(' ');

        int rootIdx = indexMap[root];
        int leftSize = rootIdx - inStart;

        buildPreorder(inStart, rootIdx - 1, postStart, postStart + leftSize - 1);
        buildPreorder(rootIdx + 1, inEnd, postStart + leftSize, postEnd - 1);
    }
}


