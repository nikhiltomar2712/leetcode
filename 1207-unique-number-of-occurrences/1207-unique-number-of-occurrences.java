class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        int[] count = new int[2001];  // range: -1000 to 1000 → shift by +1000
        
        for (int num : arr) {
            count[num + 1000]++;
        }
        
        boolean[] seen = new boolean[arr.length + 1];
        for (int c : count) {
            if (c > 0) {
                if (seen[c]) return false;
                seen[c] = true;
            }
        }
        return true;
    }
}