import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = bf.readLine().split(" ");
        long a = Long.parseLong(tokens[0]);
        long b = Long.parseLong(tokens[1]);
        long answer = Integer.MAX_VALUE;

        Queue<long[]> queue = new LinkedList<>();
        queue.add(new long[]{a, 1});

        while(!queue.isEmpty()) {
            long[] arr = queue.poll();
            long currentValue = arr[0];
            long cnt = arr[1];

            long next1 = currentValue * 2;
            long next2 = Long.parseLong(String.valueOf(currentValue) + "1");
            if (next1 == b) answer = Math.min(answer, cnt+1);
            else if (next1 <= b) queue.add(new long[]{next1, cnt+1});

            if (next2 == b) answer = Math.min(answer, cnt+1);
            else if (next2 <= b)queue.add(new long[]{next2, cnt+1});
        }
        if (answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
    }

}
