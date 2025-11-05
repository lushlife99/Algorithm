import java.io.*;
import java.util.*;


/**
 * boj 1377 버블 소트
 *
 * 알고리즘 생각
 * 시간복잡도 -> NlogN
 */

public class Main {

    static class Node implements Comparable<Node> {
        int num, idx;

        public Node(int num, int idx) {
            this.num = num; this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(num, o.num);
        }
    }

    private static int N;
    private static Node[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Node[N];
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = new Node(num, i);
        }

        Arrays.sort(arr);

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, arr[i].idx - i);
        }
        System.out.println(max+1);
    }

}
