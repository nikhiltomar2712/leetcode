class Solution {
    public int videoStitching(int[][] clips, int time) {
        // Sort by start time
        Arrays.sort(clips, (a, b) -> Integer.compare(a[0], b[0]));

        int ans = 0;
        int end = 0;        // current coverage end
        int farthest = 0;   // farthest we can reach from clips starting ≤ end
        int i = 0;
        int n = clips.length;

        while (end < time) {
            // Take all clips that start before or at current end
            while (i < n && clips[i][0] <= end) {
                farthest = Math.max(farthest, clips[i][1]);
                i++;
            }

            // Cannot extend coverage
            if (farthest == end) {
                return -1;
            }

            // Use one more clip
            ans++;
            end = farthest;
        }

        return ans;
    }
}