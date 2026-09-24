class Solution {
    public int minimumDeletions(String s) {
        int bCount = 0;   // number of 'b's seen so far
        int deletions = 0;

        for (char c : s.toCharArray()) {
            if (c == 'b') {
                bCount++;
            } else {  // 'a'
                // Either delete this 'a', or delete all previous 'b's
                deletions = Math.min(deletions + 1, bCount);
            }
        }

        return deletions;
    }
}