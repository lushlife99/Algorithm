import java.io.*;
import java.util.*;

/**
 * boj 1114 통나무 자르기
 * 메개 변수 탐색 O(K*logL)
 */


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] visited = new boolean[10];
        for (int i = 0; i < 5; i++) {
            int n = Integer.parseInt(br.readLine());
            if (visited[n]) {
                visited[n] = false;
            } else {
                visited[n] = true;
            }
        }

        for (int i = 0; i < 10; i++) {
            if (visited[i]) {
                System.out.println(i);
                break;
            }
        }
    }
}