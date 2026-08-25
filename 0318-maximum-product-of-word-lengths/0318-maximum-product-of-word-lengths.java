class Solution {
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] masks = new int[n];
        int[] lengths = new int[n];
        
        // Precompute bitmask and length for each word
        for (int i = 0; i < n; i++) {
            int mask = 0;
            for (char c : words[i].toCharArray()) {
                mask |= 1 << (c - 'a');
            }
            masks[i] = mask;
            lengths[i] = words[i].length();
        }
        
        int maxProduct = 0;
        // Check all pairs
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // If they don't share common letters
                if ((masks[i] & masks[j]) == 0) {
                    maxProduct = Math.max(maxProduct, lengths[i] * lengths[j]);
                }
            }
        }
        
        return maxProduct;
    }
}