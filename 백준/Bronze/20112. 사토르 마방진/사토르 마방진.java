import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[][] magic = new String[n][n];

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                magic[i][j] = String.valueOf(c);
            }
        }

        boolean result = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!magic[i][j].equals(magic[j][i])) {
                    result = false;
                    break;
                }
            }
        }

        if (result) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}