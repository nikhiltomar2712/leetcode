class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int pushes = 0;
        
        // For first 8 letters: 1 push each
        // For next 8 letters: 2 pushes each
        // For next 8 letters: 3 pushes each
        // For remaining 2 letters (if any): 4 pushes each
        
        if (n <= 8) {
            return n; // All letters with 1 push
        } else if (n <= 16) {
            return 8 + (n - 8) * 2; // 8 letters with 1 push, rest with 2 pushes
        } else if (n <= 24) {
            return 8 + 8 * 2 + (n - 16) * 3; // 8 with 1, 8 with 2, rest with 3
        } else {
            return 8 + 8 * 2 + 8 * 3 + (n - 24) * 4; // 8 with 1, 8 with 2, 8 with 3, rest with 4
        }
    }
}