class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> prefixSet = new HashSet<>();
        
        // Step 1: Store all prefixes of numbers from arr1
        for (int num : arr1) {
            while (num > 0) {
                prefixSet.add(num);
                num /= 10;
            }
        }
        
        // Step 2: Check prefixes of numbers from arr2
        int maxLen = 0;
        for (int num : arr2) {
            while (num > 0) {
                if (prefixSet.contains(num)) {
                    maxLen = Math.max(maxLen, String.valueOf(num).length());
                    break; // Found longest prefix for this number
                }
                num /= 10;
            }
        }
        
        return maxLen;
    }
}