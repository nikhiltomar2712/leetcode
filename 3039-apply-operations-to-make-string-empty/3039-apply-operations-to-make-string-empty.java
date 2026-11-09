class Solution {
    public String lastNonEmptyString(String s) {
        int[] freq = new int[26];
        int[] lastPos = new int[26];
        int maxFreq = 0;

        // Count frequencies and record last positions
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            freq[idx]++;
            maxFreq = Math.max(maxFreq, freq[idx]);
            lastPos[idx] = i;
        }

        // Collect characters with max frequency, ordered by last occurrence
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            // Include only at its last occurrence AND if it has max frequency
            if (freq[idx] == maxFreq && lastPos[idx] == i) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}