import java.util.*;

class Solution {
    public boolean possiblyEquals(String s1, String s2) {
        Map<Integer, Boolean>[][] mem = new Map[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); ++i) {
            for (int j = 0; j <= s2.length(); ++j) {
                mem[i][j] = new HashMap<>();
            }
        }
        return f(s1, s2, 0, 0, 0, mem);
    }

    // Returns true if s1[i..] can match s2[j..] given the current length difference.
    // paddingDiff > 0 means s1 has already "consumed" more characters than s2
    // (s2 needs to catch up). paddingDiff < 0 means the opposite.
    private boolean f(String s1, String s2, int i, int j, int paddingDiff,
                      Map<Integer, Boolean>[][] mem) {
        if (mem[i][j].containsKey(paddingDiff)) {
            return mem[i][j].get(paddingDiff);
        }
        if (i == s1.length() && j == s2.length()) {
            return paddingDiff == 0;
        }

        if (i < s1.length() && Character.isDigit(s1.charAt(i))) {
            // Parse consecutive digits in s1 and try every possible way to split them.
            int nextLetterIndex = getNextLetterIndex(s1, i);
            for (int num : getNums(s1.substring(i, nextLetterIndex))) {
                if (f(s1, s2, nextLetterIndex, j, paddingDiff + num, mem)) {
                    return true;
                }
            }
        } else if (j < s2.length() && Character.isDigit(s2.charAt(j))) {
            // Parse consecutive digits in s2 and try every possible way to split them.
            int nextLetterIndex = getNextLetterIndex(s2, j);
            for (int num : getNums(s2.substring(j, nextLetterIndex))) {
                if (f(s1, s2, i, nextLetterIndex, paddingDiff - num, mem)) {
                    return true;
                }
            }
        } else if (paddingDiff > 0) {
            // s1 is ahead → consume one character from s2.
            if (j < s2.length()) {
                return f(s1, s2, i, j + 1, paddingDiff - 1, mem);
            }
        } else if (paddingDiff < 0) {
            // s2 is ahead → consume one character from s1.
            if (i < s1.length()) {
                return f(s1, s2, i + 1, j, paddingDiff + 1, mem);
            }
        } else { // paddingDiff == 0
            // Both sides are balanced → the next characters must be equal letters.
            if (i < s1.length() && j < s2.length() && s1.charAt(i) == s2.charAt(j)) {
                return f(s1, s2, i + 1, j + 1, 0, mem);
            }
        }

        mem[i][j].put(paddingDiff, false);
        return false;
    }

    private int getNextLetterIndex(String s, int i) {
        int j = i;
        while (j < s.length() && Character.isDigit(s.charAt(j))) {
            ++j;
        }
        return j;
    }

    // All possible lengths a digit string of length ≤ 3 can represent.
    // e.g. "12" → {12, 1+2}
    //      "123" → {123, 1+23, 12+3, 1+2+3}
    private List<Integer> getNums(String s) {
        List<Integer> nums = new ArrayList<>();
        nums.add(Integer.parseInt(s));
        if (s.length() == 2) {
            nums.add(Integer.parseInt(s.substring(0, 1)) + Integer.parseInt(s.substring(1, 2)));
        } else if (s.length() == 3) {
            nums.add(Integer.parseInt(s.substring(0, 1)) + Integer.parseInt(s.substring(1, 3)));
            nums.add(Integer.parseInt(s.substring(0, 2)) + Integer.parseInt(s.substring(2, 3)));
            nums.add(Integer.parseInt(s.substring(0, 1)) + Integer.parseInt(s.substring(1, 2))
                    + Integer.parseInt(s.substring(2, 3)));
        }
        return nums;
    }
}