import java.io.*;
import java.util.*;

/**
 * boj 2457 공주님의 정원
 * 정렬, 우선순위 큐
 */

public class Main {

    static int N;
    static List<int[]> flowers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());

            if (sm > 11) continue;
            if (em < 3) continue;

            if (sm < 3) {
                sm = 3;
                sd = 1;
            }

            if (em > 11) {
                em = 12;
                ed = 1;
            }

            flowers.add(new int[]{sm, sd, em, ed});
        }

        Collections.sort(flowers, (f1, f2) -> {
            if (f1[0] == f2[0] && f1[1] == f2[1]) {
                if (f1[2] != f2[2]) return f2[2] - f1[2];
                return f2[3] - f1[3];
            }

            if (f1[0] != f2[0]) return f1[0] - f2[0];
            return f1[1] - f2[1];
        });

        int cm = 3;
        int cd = 1;
        int answer = 0;

        Queue<int[]> pq = new PriorityQueue<>((f1, f2) -> {
            if (f1[2] != f2[2]) return f2[2] - f1[2];
            return f2[3] - f1[3];
        });

        for (int i = 0; i < flowers.size(); i++) {
            int[] flower = flowers.get(i);

            if (flower[2] < cm || (flower[2] == cm && flower[3] < cd)) {
                continue;
            }

            if (flower[0] < cm || (flower[0] == cm && flower[1] <= cd)) {
                pq.add(flower);
                continue;
            }

            if (pq.isEmpty()) {
                break;
            }

            int[] selectFlower = pq.poll();
            cm = selectFlower[2];
            cd = selectFlower[3];
            answer++;


            if (flower[0] < cm || (flower[0] == cm && flower[1] <= cd)) {
                pq.add(flower);
            }

        }

        if (cm == 12 && cd == 1) {
            System.out.println(answer);
            return;
        }

        if (!pq.isEmpty()) {
            int[] flower = pq.poll();
            if (flower[2] == 12 && flower[3] == 1) {
                System.out.println(answer+1);
                return;
            }
        }

        System.out.println(0);
    }
}

