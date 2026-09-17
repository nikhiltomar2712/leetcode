import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        final int INF = Integer.MAX_VALUE / 2;

        // minLenAtOrBefore[i] = min length of a target-sum subarray ending at or before i
        int[] minLenAtOrBefore = new int[n];
        Arrays.fill(minLenAtOrBefore, INF);

        int sum = 0, left = 0;
        int best = INF;
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left++];
            }
            if (sum == target) {
                best = Math.min(best, right - left + 1);
            }
            minLenAtOrBefore[right] = best;
        }

        // Second pass: combine with a non-overlapping earlier subarray
        sum = 0;
        left = 0;
        int result = INF;
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left++];
            }
            if (sum == target) {
                int curLen = right - left + 1;
                if (left > 0 && minLenAtOrBefore[left - 1] != INF) {
                    result = Math.min(result, curLen + minLenAtOrBefore[left - 1]);
                }
            }
        }

        return result == INF ? -1 : result;
    }
}