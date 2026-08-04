class Solution {
    public int bestRotation(int[] nums) {
        int n = nums.length;
        int[] diff = new int[n];          // difference array

        for (int i = 0; i < n; i++) {
            // The rotation k where nums[i] starts scoring a point
            int left  = (i + 1) % n;
            // The rotation k where nums[i] stops scoring a point
            int right = (i - nums[i] + 1 + n) % n;

            diff[left]++;
            diff[right]--;
        }

        int maxScore = -1;
        int bestK = 0;
        int score = 0;

        for (int k = 0; k < n; k++) {
            score += diff[k];
            if (score > maxScore) {
                maxScore = score;
                bestK = k;
            }
        }
        return bestK;
    }
}