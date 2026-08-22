class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int deletions = 0;
        
        // sorted[i] = true means strs[i] is already strictly < strs[i+1]
        // based on the columns we have kept so far
        boolean[] sorted = new boolean[n - 1];
        
        for (int col = 0; col < m; col++) {
            // Check if this column can be kept
            boolean canKeep = true;
            
            for (int row = 0; row < n - 1; row++) {
                if (!sorted[row] && strs[row].charAt(col) > strs[row + 1].charAt(col)) {
                    canKeep = false;
                    break;
                }
            }
            
            if (!canKeep) {
                deletions++;
                continue;
            }
            
            // We can keep this column → update the sorted status
            for (int row = 0; row < n - 1; row++) {
                if (strs[row].charAt(col) < strs[row + 1].charAt(col)) {
                    sorted[row] = true;
                }
            }
        }
        
        return deletions;
    }
}