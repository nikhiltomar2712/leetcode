import java.util.*;

class Solution {
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> ans = new ArrayList<>();
        List<Integer> indicesA = kmp(s, a);
        List<Integer> indicesB = kmp(s, b);

        int j = 0;
        for (int i : indicesA) {
            // Advance j while indicesB[j] is too far to the left (j - i < -k)
            while (j < indicesB.size() && indicesB.get(j) - i < -k) {
                j++;
            }
            // Check if the current j is within k to the right (j - i <= k)
            if (j < indicesB.size() && indicesB.get(j) - i <= k) {
                ans.add(i);
            }
        }
        return ans;
    }

    // Returns all starting indices where pattern occurs in s
    private List<Integer> kmp(String s, String pattern) {
        List<Integer> res = new ArrayList<>();
        if (pattern.isEmpty()) return res;

        int[] lps = getLPS(pattern);
        int i = 0, j = 0;

        while (i < s.length()) {
            if (s.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) {
                    res.add(i - j);
                    j = lps[j - 1];
                }
            } else if (j != 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return res;
    }

    // Computes the Longest Proper Prefix which is also Suffix array
    private int[] getLPS(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0;
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}