import java.io.*;
import java.util.*;

/**
 * boj 20119 클레어와 물약
 * 위상 정렬
 *
 */

public class Main {

    static int N;
    static int M;
    static List<List<Integer>> edges = new ArrayList<>();
    static List<Set<Integer>> recipes = new ArrayList<>();
    static List<Integer> results = new ArrayList<>();
    static int L;
    static Set<Integer> visited = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            Set<Integer> recipe = new HashSet<>();

            String[] split = br.readLine().split(" ");
            int u = Integer.parseInt(split[split.length-1]);
            results.add(u);
            for (int j = 1; j < split.length-1; j++) {
                int v = Integer.parseInt(split[j]);
                recipe.add(v);
                edges.get(v).add(i);
            }
            recipes.add(recipe);
        }

        L = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < L; i++) {
            visited.add(Integer.parseInt(st.nextToken()));
        }

        q.addAll(visited);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int recipeIdx : edges.get(u)) {
                recipes.get(recipeIdx).remove(u);

                int res = results.get(recipeIdx);
                if (recipes.get(recipeIdx).size() == 0 && !visited.contains(res)) {
                    q.add(res);
                    visited.add(res);
                }
            }
        }

        List<Integer> answer = new ArrayList<>(visited);
        Collections.sort(answer);

        bw.write(answer.size() + "\n");
        for (Integer i : answer) {
            bw.write(i + " ");
        }

        bw.flush();
        bw.close();
        br.close();

    }

}
