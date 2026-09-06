class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        // Count frequency of each word's bitmask
        Map<Integer, Integer> count = new HashMap<>();
        for (String word : words) {
            int mask = 0;
            for (char c : word.toCharArray()) {
                mask |= 1 << (c - 'a');
            }
            count.put(mask, count.getOrDefault(mask, 0) + 1);
        }

        List<Integer> answer = new ArrayList<>();
        
        for (String puzzle : puzzles) {
            int mask = 0;
            for (char c : puzzle.toCharArray()) {
                mask |= 1 << (c - 'a');
            }
            
            int first = puzzle.charAt(0) - 'a';
            int total = 0;
            
            // Enumerate all subsets of the puzzle mask
            for (int sub = mask; sub > 0; sub = (sub - 1) & mask) {
                // Only count subsets that contain the first letter
                if ((sub & (1 << first)) != 0) {
                    total += count.getOrDefault(sub, 0);
                }
            }
            
            answer.add(total);
        }
        
        return answer;
    }
}
