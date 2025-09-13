import java.util.*;

/**
 * boj 1294 문자열 장식
 * 예제는 다 맞췄는데 틀렸음. 머리 안돌아가서 답지봄
 */

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        PriorityQueue<StringBuffer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            queue.add(new StringBuffer(in.next() + "a"));
        }

        while (!queue.isEmpty()) {
            StringBuffer alphabet = queue.poll();

            System.out.print(alphabet.charAt(0));
            alphabet.deleteCharAt(0);

            if (alphabet.length() > 1) {
                queue.add(alphabet);
            }
        }
        System.out.println();
    }
}