class Solution {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        int[] res = new int[n];
        int[] tails = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            int x = obstacles[i];
            int left = 0, right = len;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] <= x) left = mid + 1;
                else right = mid;
            }
            tails[left] = x;
            if (left == len) len++;
            res[i] = left + 1;
        }
        return res;
    }
}