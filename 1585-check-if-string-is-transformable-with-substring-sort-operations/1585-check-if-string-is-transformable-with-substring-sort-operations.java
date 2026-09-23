import java.util.*;

class Solution {
    public boolean isTransformable(String s, String t) {
        int n = s.length();
        if (s.length() != t.length()) return false;

        // queues of indices for each digit in s
        Deque<Integer>[] pos = new Deque[10];
        for (int d = 0; d < 10; d++) pos[d] = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            pos[s.charAt(i) - '0'].add(i);
        }

        // counts must match
        int[] cntS = new int[10], cntT = new int[10];
        for (char c : s.toCharArray()) cntS[c - '0']++;
        for (char c : t.toCharArray()) cntT[c - '0']++;
        if (!Arrays.equals(cntS, cntT)) return false;

        for (int i = 0; i < n; i++) {
            int d = t.charAt(i) - '0';
            if (pos[d].isEmpty()) return false; // safety, shouldn't happen after count check
            int idx = pos[d].pollFirst();

            // check no smaller digit blocks this occurrence
            for (int smaller = 0; smaller < d; smaller++) {
                if (!pos[smaller].isEmpty() && pos[smaller].peekFirst() < idx) {
                    return false;
                }
            }
        }

        return true;
    }
}