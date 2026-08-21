class Solution {
    public int minDeletionSize(String[] strs) {
        int rows = strs.length;
        int cols = strs[0].length();
        int deleteCount = 0;
        
        for (int c = 0; c < cols; c++) {
            for (int r = 1; r < rows; r++) {
                // If current character is smaller than the one above → not sorted
                if (strs[r].charAt(c) < strs[r - 1].charAt(c)) {
                    deleteCount++;
                    break; // no need to check further in this column
                }
            }
        }
        
        return deleteCount;
    }
}