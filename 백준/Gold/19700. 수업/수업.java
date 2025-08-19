import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 19700 수업
 * 그리디
 */

public class Main {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    private void solution() throws Exception {
        int n = Integer.parseInt(br.readLine());

        List<int[]> tasks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int score = Integer.parseInt(st.nextToken());
            int condition = Integer.parseInt(st.nextToken());
            tasks.add(new int[]{score, condition});
        }

        // 점수 내림차순 정렬
        tasks.sort(Comparator.comparingInt((int[] o) -> o[0]).reversed());

        TreeSet<Integer> groups = new TreeSet<>();
        int[] groupCounts = new int[n + 1];
        groupCounts[0] = n;

        for (int[] task : tasks) {
            int condition = task[1];

            Integer prevGroupSize = groups.lower(condition);
            if (prevGroupSize == null) prevGroupSize = 0;

            if (--groupCounts[prevGroupSize] == 0) {
                groups.remove(prevGroupSize);
            }

            int newGroupSize = prevGroupSize + 1;
            groupCounts[newGroupSize]++;
            groups.add(newGroupSize);
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer += groupCounts[i];
        }

        System.out.println(answer);
    }
}
