class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        List<int[]>[] buckets = new List[26];
        for (int i = 0; i < 26; i++) {
            buckets[i] = new ArrayList<>();
        }

        // each entry = {wordIndex, charIndex}
        for (int i = 0; i < words.length; i++) {
            buckets[words[i].charAt(0) - 'a'].add(new int[]{i, 0});
        }

        int count = 0;

        for (char c : s.toCharArray()) {
            List<int[]> current = buckets[c - 'a'];
            buckets[c - 'a'] = new ArrayList<>();

            for (int[] pair : current) {
                int wordIdx = pair[0];
                int charIdx = pair[1] + 1;

                if (charIdx == words[wordIdx].length()) {
                    count++;
                } else {
                    char next = words[wordIdx].charAt(charIdx);
                    buckets[next - 'a'].add(new int[]{wordIdx, charIdx});
                }
            }
        }
        return count;
    }
}