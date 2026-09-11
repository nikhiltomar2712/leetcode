class Solution {
    public int minSetSize(int[] arr) {
        int n = arr.length;
        
        // Count frequencies
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : arr) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }
        
        // Sort frequencies descending
        int[] counts = new int[freq.size()];
        int idx = 0;
        for (int c : freq.values()) {
            counts[idx++] = c;
        }
        Arrays.sort(counts);
        
        // Greedily take largest frequencies
        int target = n / 2;
        int removed = 0;
        int setSize = 0;
        
        for (int i = counts.length - 1; i >= 0; i--) {
            removed += counts[i];
            setSize++;
            if (removed >= target) {
                return setSize;
            }
        }
        
        return setSize; // should always return within the loop for valid input
    }
}