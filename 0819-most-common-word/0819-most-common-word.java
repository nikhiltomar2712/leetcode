import java.util.*;

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        // 1. Put banned words into a set
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));

        // 2. Normalize the paragraph: lowercase + replace all non-letters with space
        String normalized = paragraph.toLowerCase().replaceAll("[^a-z]", " ");

        // 3. Split into words
        String[] words = normalized.split("\\s+");

        // 4. Count frequencies of non-banned words
        Map<String, Integer> count = new HashMap<>();
        String mostCommon = "";
        int maxFreq = 0;

        for (String word : words) {
            if (word.isEmpty() || bannedSet.contains(word)) {
                continue;
            }
            int freq = count.getOrDefault(word, 0) + 1;
            count.put(word, freq);

            if (freq > maxFreq) {
                maxFreq = freq;
                mostCommon = word;
            }
        }

        return mostCommon;
    }
}