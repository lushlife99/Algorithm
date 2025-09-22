import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 5670 휴대폰 자판
 * 문자열
 */


public class Main {


    private static class Node {

        char c;
        Node parent;
        Map<Character, Node> childs = new HashMap<>();

        public Node() {}

        public Node(char c, Node p) {
            this.c = c;
            this.parent = p;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String s = br.readLine();

            if (s == null) break;

            Node root = new Node();
            List<String> words = new ArrayList<>();
            int N = Integer.parseInt(s);

            for (int i = 0; i < N; i++) {
                String word = br.readLine();
                words.add(word);
                Node parent = root;

                for (int j = 0; j < word.length(); j++) {
                    char c = word.charAt(j);
                    Node node = new Node(c, parent);
                    parent.childs.putIfAbsent(c, node);
                    parent = parent.childs.get(c);

                    if (j == word.length()-1) {
                        parent.childs.put('\u0000', new Node('\u0000', parent));
                    }
                }

            }

            int sum = 0;
            for (String word : words) {
                sum += getInputCnt(word, root);
            }

            System.out.printf("%.2f\n", (float)sum / N);
        }

    }

    private static int getInputCnt(String word, Node root) {

        int cnt = 1;
        root = root.childs.get(word.charAt(0));

        for (int i = 1; i < word.length(); i++) {
            if (root.childs.size() > 1) {
                cnt++;
            }

            root = root.childs.get(word.charAt(i));
        }

        return cnt;
    }

}


