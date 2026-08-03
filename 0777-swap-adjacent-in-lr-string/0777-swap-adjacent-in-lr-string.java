class Solution {
    public boolean canTransform(String start, String result) {
        int n = start.length();
        int i = 0, j = 0;

        while (true) {
            // Skip all 'X's
            while (i < n && start.charAt(i) == 'X') i++;
            while (j < n && result.charAt(j) == 'X') j++;

            // Both finished → success
            if (i == n && j == n) return true;

            // One finished before the other, or characters differ
            if (i == n || j == n || start.charAt(i) != result.charAt(j)) {
                return false;
            }

            // Direction constraints
            if (start.charAt(i) == 'L' && i < j) return false; // L can't move right
            if (start.charAt(i) == 'R' && i > j) return false; // R can't move left

            i++;
            j++;
        }
    }
}