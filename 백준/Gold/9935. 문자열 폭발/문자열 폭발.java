import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * boj 9935 문자열 폭발
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String bomb = br.readLine();
        int bombLen = bomb.length();

        char[] stack = new char[str.length()];
        int top = 0;

        for (int i = 0; i < str.length(); i++) {
            stack[top++] = str.charAt(i);

            if (top >= bombLen) {
                boolean isBomb = true;
                for (int j = 0; j < bombLen; j++) {
                    if (stack[top - bombLen + j] != bomb.charAt(j)) {
                        isBomb = false;
                        break;
                    }
                }

                if (isBomb) {
                    top -= bombLen;
                }
            }
        }

        if (top == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(new String(stack, 0, top));
        }
    }
}