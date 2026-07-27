class Solution {
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // Count how many numbers in the table are ≤ mid
            int count = 0;
            for (int i = 1; i <= m; i++) {
                count += Math.min(mid / i, n);
            }
            
            if (count >= k) {
                right = mid;          // mid is large enough (or too large)
            } else {
                left = mid + 1;       // need a larger number
            }
        }
        
        return left;
    }
}