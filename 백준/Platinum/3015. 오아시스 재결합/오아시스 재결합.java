import java.io.*;
import java.util.*;

/**
 * boj 3015 오아시스 재결합
 * 스택
 */

/**
 * 반례 생각하기
 * - 1이 50만개
 * -> 시간 초과
 * 433
 * <p>
 * 해결 방법
 * -
 */


public class Main {

    static class Node {
        int value, cnt;

        public Node(int value, int cnt) {
            this.value = value;
            this.cnt = cnt;
        }
    }

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        long answer = 0;
        Stack<Node> stk = new Stack<>();

        for (int i = 0; i < N; i++) {
            int current = Integer.parseInt(br.readLine());

            if (stk.isEmpty()) {
                stk.push(new Node(current, 1));
                continue;
            }

            while (!stk.isEmpty()) { // 반례 똑같은 거 여러개
                Node prev = stk.pop();

                if (prev.value > current) {
                    stk.push(prev);
                    stk.push(new Node(current, 1));
                    answer++;
                    break;
                } else if (prev.value == current) {
                    if (stk.isEmpty()) {
                        answer += prev.cnt;
                    } else {
                        answer += prev.cnt + 1;
                    }
                    stk.push(new Node(current, prev.cnt + 1));
                    break;
                } else {
                    answer += prev.cnt;

                }
            }

            if (stk.isEmpty()) {
                stk.push(new Node(current, 1));
            }
        }

        System.out.println(answer);
    }
}

