class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        // Find the rightmost position j where we can place a char > target[j]
        // while matching the prefix of target up to j.
        int[] tmp = cnt.clone();
        int j = -1;
        for (int i = 0; i < n; i++) {
            char x = target.charAt(i);
            // Is there any letter strictly greater than x available?
            if (nextGreater(tmp, x) != ' ') {
                j = i;
            }
            if (tmp[x - 'a'] == 0) {
                break;          // cannot match the prefix any further
            }
            tmp[x - 'a']--;
        }

        if (j == -1) {
            return "";          // no permutation is strictly greater
        }

        // Build the answer: copy target[0..j-1], then the smallest
        // letter > target[j], then the remaining letters in sorted order.
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < j; i++) {
            res.append(target.charAt(i));
            cnt[target.charAt(i) - 'a']--;
        }

        char y = nextGreater(cnt, target.charAt(j));
        res.append(y);
        cnt[y - 'a']--;

        // Append remaining characters in non-decreasing order
        for (int c = 0; c < 26; c++) {
            while (cnt[c]-- > 0) {
                res.append((char) ('a' + c));
            }
        }
        return res.toString();
    }

    // Returns the smallest available letter strictly greater than x, or ' ' if none.
    private char nextGreater(int[] cnt, char x) {
        for (int i = x - 'a' + 1; i < 26; i++) {
            if (cnt[i] > 0) {
                return (char) ('a' + i);
            }
        }
        return ' ';
    }
}