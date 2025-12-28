import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 1700 멀티탭 스케줄링
 * 그리디
 *
 * 가장 나중에 사용하는 플러그 빼기
 */


public class Main {

    static class Node {
        int id;
        int idx;
        List<Integer> schedules;

        Node (int id, int idx) {
            this.id = id;
            this.idx = idx;
            schedules = new ArrayList<>();
        }

        int getNewestUsedTime(int time) {
            for (; idx < schedules.size(); idx++) {
                if (schedules.get(idx) <= time) continue;
                return schedules.get(idx);
            }

            return 1000;
        }
    }

    static int N, K;
    static Set<Integer> multiTap = new HashSet<>();
    static Node[] nodes;
    static int[] schedule;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        schedule = new int[K];
        nodes = new Node[K+1];

        for (int i = 1; i <= K; i++) {
            nodes[i] = new Node(i, 0);
        }

        for (int i = 0; i < K; i++) {
            schedule[i] = Integer.parseInt(st.nextToken());
            nodes[schedule[i]].schedules.add(i);
        }

        int answer = 0;

        for (int i = 0; i < K; i++) {
            int node = schedule[i];

            if (multiTap.contains(node)) continue;
            if (multiTap.size() < N) {
                multiTap.add(node);
                continue;
            }

            int maxTime = 0;
            int maxNodeIdx = 0;
            for (int nodeIdx : multiTap) {
                if (maxTime < nodes[nodeIdx].getNewestUsedTime(i)) {
                    maxTime = nodes[nodeIdx].getNewestUsedTime(i);
                    maxNodeIdx = nodeIdx;
                }
            }

            multiTap.remove(maxNodeIdx);
            multiTap.add(node);
            answer++;
        }

        System.out.println(answer);
    }
}
