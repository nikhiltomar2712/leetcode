class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(piles, mid, h)) {
                right = mid;          // try slower speed
            } else {
                left = mid + 1;       // need faster speed
            }
        }
        return left;
    }

    private boolean canFinish(int[] piles, int k, int h) {
        long hours = 0;               // use long to be safe
        for (int pile : piles) {
            // ceil(pile / k) = (pile + k - 1) / k
            hours += (pile + k - 1L) / k;
            if (hours > h) return false;  // early exit
        }
        return hours <= h;
    }
}