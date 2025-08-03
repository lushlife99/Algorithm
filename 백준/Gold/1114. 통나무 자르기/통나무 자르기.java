import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ 1114 통나무 자르기
 *
 * 테스트케이스는 맞았는데, 반례 못찾겠음
 * 결국 접근방법이 아예 틀린거같아서 답지봄
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        List<Integer> pos = new ArrayList<>();
        pos.add(0); pos.add(L);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            pos.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(pos);

        int left = 0;
        int right = L;
        int answerFirstCutPos = 0;
        int maxCutLength = Integer.MAX_VALUE;

        while (left < right) {
            int mid = (left + right) / 2;
            int length = 0;
            int cutCnt = 0;

            for (int i = K; i >= 0; i--) {
                length += pos.get(i+1) - pos.get(i);
                if (length > mid) {
                    length = pos.get(i+1) - pos.get(i);
                    cutCnt++;
                    if (length > mid) {
                        cutCnt = C+1;
                        break;
                    }
                }
            }

            if (cutCnt <= C) {
                int firstCutPos = cutCnt < C ? pos.get(1) : length;
                
                answerFirstCutPos = firstCutPos;
                maxCutLength = Math.min(maxCutLength, mid);
                right = mid;
            } else {
                left = mid+1;
            }
        }

        System.out.printf("%d %d", maxCutLength, answerFirstCutPos);

    }
}