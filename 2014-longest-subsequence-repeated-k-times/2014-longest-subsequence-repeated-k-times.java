class Solution {
    public String longestSubsequenceRepeatedK(String s, int k) {
        char[] arr = s.toCharArray();
        int[] freq = new int[26];
        for (char c : arr) {
            freq[c - 'a']++;
        }

        // Only characters that appear ≥ k times can be part of the answer
        List<Character> candidates = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            if (freq[c - 'a'] >= k) {
                candidates.add(c);
            }
        }

        // BFS: longer strings are generated later → the last valid one is the longest
        // Appending characters in alphabetical order makes the last one of that length
        // also the lexicographically largest.
        Queue<String> q = new ArrayDeque<>();
        q.offer("");
        String ans = "";

        while (!q.isEmpty()) {
            String cur = q.poll();
            for (char c : candidates) {
                String next = cur + c;
                if (isKRepeated(arr, next, k)) {
                    ans = next;          // keep updating – last one is optimal
                    q.offer(next);
                }
            }
        }
        return ans;
    }

    // Check whether t repeated k times is a subsequence of s
    private boolean isKRepeated(char[] s, String t, int k) {
        int i = 0;                       // pointer inside t
        for (char c : s) {
            if (c == t.charAt(i)) {
                if (++i == t.length()) {
                    if (--k == 0) return true;
                    i = 0;               // start the next repetition
                }
            }
        }
        return false;
    }
}