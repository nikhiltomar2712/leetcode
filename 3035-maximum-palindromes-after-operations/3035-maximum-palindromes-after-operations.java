class Solution {
    public int maxPalindromesAfterOperations(String[] words) {
        int[] freq = new int[26];
        int[] lengths = new int[words.length];
        int pairs = 0;

        // Count character frequencies and collect word lengths
        for (int i = 0; i < words.length; i++) {
            lengths[i] = words[i].length();
            for (char c : words[i].toCharArray()) {
                freq[c - 'a']++;
                // Every time a count becomes even, we gain one pair
                if (freq[c - 'a'] % 2 == 0) {
                    pairs++;
                }
            }
        }

        // Sort word lengths ascending to greedily form shorter palindromes first
        Arrays.sort(lengths);

        int result = 0;
        for (int len : lengths) {
            int neededPairs = len / 2;
            if (pairs >= neededPairs) {
                pairs -= neededPairs;
                result++;
            } else {
                break;
            }
        }

        return result;
    }
}