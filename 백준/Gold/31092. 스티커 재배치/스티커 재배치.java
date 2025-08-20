import java.io.*;
import java.util.*;

/**
 * boj 31092 스티커 재배치
 *
 * 구현, 그리디
 */

public class Main {
    static final int INF = (int) 1e9;

    static int N, M, K;
    static String[][] stickerInfo;
    static int[] boardStickerIndex;
    static String targetWord;
    static Map<String, Integer> minReplaceCost = new HashMap<>();
    static List<String> stickerNames = new ArrayList<>();
    static Map<String, Integer> requiredSticker = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        K = Integer.parseInt(stz.nextToken());

        stickerInfo = new String[M][3];
        for (int i = 0; i < M; i++) {
            stickerInfo[i] = br.readLine().trim().split(" ");
            String name = stickerInfo[i][0];
            int cost1 = Integer.parseInt(stickerInfo[i][1]);
            int cost2 = Integer.parseInt(stickerInfo[i][2]);

            stickerInfo[i][1] = String.valueOf(cost1);
            stickerInfo[i][2] = String.valueOf(cost2);

            if (stickerNames.contains(name)) {
                minReplaceCost.put(name, Math.min(minReplaceCost.get(name), cost2));
            } else {
                minReplaceCost.put(name, cost2);
                stickerNames.add(name);
            }
        }

        boardStickerIndex = new int[N];
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            boardStickerIndex[i] = Integer.parseInt(stz.nextToken());
        }

        targetWord = br.readLine().trim();

        for (String t : stickerNames) requiredSticker.put(t, 0);
        if (!validateTarget(targetWord)) {
            System.out.println(-1);
            return;
        }

        int answer = INF;
        for (int start = 0; start <= N - K; start++) {
            int costSum = 0;
            Map<String, PriorityQueue<Integer>> availableStickers = new HashMap<>();
            for (String key : stickerNames) {
                availableStickers.put(key, new PriorityQueue<>());
            }

            // 보드 전체 순회
            for (int pos = 0; pos < N; pos++) {
                String stickerName = stickerInfo[boardStickerIndex[pos] - 1][0];
                int cost = Integer.parseInt(stickerInfo[boardStickerIndex[pos] - 1][1]);

                if (start <= pos && pos <= start + K - 1) {
                    if (targetWord.charAt(pos - start) != stickerName.charAt(0)) {
                        if (requiredSticker.get(stickerName) == 1) {
                            availableStickers.get(stickerName).add(0);
                        }
                        costSum += cost;
                    }
                } else {
                    if (requiredSticker.get(stickerName) == 1) {
                        availableStickers.get(stickerName).add(cost);
                    }
                }
            }

            // target 구간 검사
            for (int pos = start; pos < start + K; pos++) {
                String stickerName = stickerInfo[boardStickerIndex[pos] - 1][0];
                if (targetWord.charAt(pos - start) != stickerName.charAt(0)) {
                    String needChar = String.valueOf(targetWord.charAt(pos - start));
                    if (!availableStickers.get(needChar).isEmpty()) {
                        int v = availableStickers.get(needChar).poll();
                        if (v <= minReplaceCost.get(needChar)) {
                            costSum += v;
                        } else {
                            costSum += minReplaceCost.get(needChar);
                            availableStickers.get(needChar).add(v);
                        }
                    } else {
                        costSum += minReplaceCost.get(needChar);
                    }
                }
            }

            answer = Math.min(answer, costSum);
        }

        System.out.println(answer);
    }

    static boolean validateTarget(String target) {
        for (int i = 0; i < K; i++) {
            String ch = String.valueOf(target.charAt(i));
            if (!stickerNames.contains(ch)) {
                return false;
            } else {
                requiredSticker.put(ch, 1);
            }
        }
        return true;
    }
}
