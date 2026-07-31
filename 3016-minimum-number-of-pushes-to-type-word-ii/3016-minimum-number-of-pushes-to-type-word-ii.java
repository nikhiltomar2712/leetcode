class Solution {
    public int minimumPushes(String word) {
        // Step 1: Count frequency of each character (a-z)
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        // Step 2: Sort frequencies in descending order
        // We only need the frequencies that are > 0
        Integer[] counts = Arrays.stream(freq).boxed().toArray(Integer[]::new);
        Arrays.sort(counts, Collections.reverseOrder());

        // Step 3: Calculate minimum pushes
        int totalPushes = 0;
        int presses = 1;        // Starting from 1 push per key press
        int keysUsed = 0;       // Number of keys mapped in current press level

        for (int count : counts) {
            if (count == 0) break; // No more letters to map

            totalPushes += count * presses;
            keysUsed++;

            // Every 8 keys, the number of pushes required increases by 1
            if (keysUsed == 8) {
                keysUsed = 0;
                presses++;
            }
        }

        return totalPushes;
    }
}