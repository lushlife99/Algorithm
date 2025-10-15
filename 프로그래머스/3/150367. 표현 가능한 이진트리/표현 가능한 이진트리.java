import java.util.*;

class Solution {

    private static final List<Integer> sizes = new ArrayList<>();

    static {
        int num = 1;
        while (num < (1 << 62)) {
            sizes.add(num);
            num = num * 2 + 1;
        }
    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);
            binary = pad(binary);
            answer[i] = isValid(binary) ? 1 : 0;
        }
        return answer;
    }

    private String pad(String binary) {
        for (int s : sizes) {
            if (binary.length() <= s) {
                return "0".repeat(s - binary.length()) + binary;
            }
        }
        return binary;
    }

    private boolean isValid(String binary) {
        return dfs(binary, 0, binary.length() - 1, false);
    }

    private boolean dfs(String binary, int start, int end, boolean parentZero) {
        if (start > end) return true;
        int mid = (start + end) / 2;
        boolean currentZero = binary.charAt(mid) == '0';

        if (parentZero && !currentZero) return false;

        return dfs(binary, start, mid - 1, currentZero)
            && dfs(binary, mid + 1, end, currentZero);
    }
}
