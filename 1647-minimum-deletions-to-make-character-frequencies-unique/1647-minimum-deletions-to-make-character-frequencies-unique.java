import java.util.*;

class Solution {
    public int minDeletions(String s) {
        // Count frequencies of each character
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Collect non-zero frequencies and sort descending
        List<Integer> freqs = new ArrayList<>();
        for (int f : freq) {
            if (f > 0) freqs.add(f);
        }
        Collections.sort(freqs, Collections.reverseOrder());

        Set<Integer> used = new HashSet<>();
        int deletions = 0;

        for (int f : freqs) {
            while (f > 0 && used.contains(f)) {
                f--;
                deletions++;
            }
            if (f > 0) used.add(f);
        }

        return deletions;
    }
}