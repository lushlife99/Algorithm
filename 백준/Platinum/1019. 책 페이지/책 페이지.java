import java.io.*;
import java.util.stream.IntStream;

/**
 * 함수형 프로그래밍으로 푸는 연습
 * boj 1019 책 페이지
 * 
 * 답지봄
 */

public class Main {

    static int[] res;
    static int cnt = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        res = new int[10];
        int start = 1;
        int end = Integer.parseInt(br.readLine());

        while (start <= end) {

            while (start % 10 != 0 && start <= end) {
                count(start);
                start++;
            }

            while (end % 10 != 9 && start <= end) {
                count(end);
                end--;
            }

            if (start > end) break;

            start /= 10;
            end /= 10;

            for (int i = 0; i < 10; i++) {
                res[i] += (end - start + 1) * cnt;
            }

            cnt *= 10;
        }

        String answer = IntStream.range(0, 10)
                .mapToObj(i -> String.valueOf(res[i]))
                .reduce((a, b) -> a + " " + b)
                .get();

        System.out.println(answer);
    }

    private static void count(int num) {
        while (num > 0) {
            res[num%10] += cnt;
            num /= 10;
        }
    }

}
