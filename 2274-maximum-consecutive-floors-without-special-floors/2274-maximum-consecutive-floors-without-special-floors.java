class Solution {
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        
        int max = 0;
        int n = special.length;
        
        // Gap from bottom to the first special floor
        max = Math.max(max, special[0] - bottom);
        
        // Gaps between consecutive special floors
        for (int i = 1; i < n; i++) {
            max = Math.max(max, special[i] - special[i - 1] - 1);
        }
        
        // Gap from the last special floor to top
        max = Math.max(max, top - special[n - 1]);
        
        return max;
    }
}