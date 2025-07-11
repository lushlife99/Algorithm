import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static Map<Integer, Integer> arrPlusCntMap = new HashMap<>();
    static Map<Integer, Integer> arr2PlusCntMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        String[] split = br.readLine().split(" ");
        int[] arr1 = new int[N];
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(split[i]);
        }
        arrPlusCntMap = getPlusCntMap(arr1);

        int M = Integer.parseInt(br.readLine());
        split = br.readLine().split(" ");
        int[] arr2 = new int[M];
        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(split[i]);
        }
        arr2PlusCntMap = getPlusCntMap(arr2);

        long answer = 0;
        for (Integer key : arrPlusCntMap.keySet()) {
            int target = T - key;
            if (arr2PlusCntMap.containsKey(target)) {
                answer += (long) arrPlusCntMap.get(key) * arr2PlusCntMap.get(target);
            }
        }
        System.out.println(answer);
    }

    static Map<Integer, Integer> getPlusCntMap(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        return map;
    }
}
