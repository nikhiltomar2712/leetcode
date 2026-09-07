import java.util.*;

class Solution {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        // Sort and remove duplicates from arr2
        Arrays.sort(arr2);
        int m = 0;
        for (int x : arr2) {
            if (m == 0 || x != arr2[m - 1]) {
                arr2[m++] = x;
            }
        }

        // dp: previous value → minimum operations to reach here
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(-1, 0);  // virtual previous value before the first element

        for (int a : arr1) {
            Map<Integer, Integer> newDp = new HashMap<>();

            for (Map.Entry<Integer, Integer> entry : dp.entrySet()) {
                int prev = entry.getKey();
                int steps = entry.getValue();

                // Option 1: Keep the current value from arr1
                if (a > prev) {
                    newDp.put(a, Math.min(newDp.getOrDefault(a, Integer.MAX_VALUE), steps));
                }

                // Option 2: Replace with the smallest value in arr2 that is > prev
                int idx = upperBound(arr2, prev, m);
                if (idx < m) {
                    int replaceVal = arr2[idx];
                    newDp.put(replaceVal, Math.min(newDp.getOrDefault(replaceVal, Integer.MAX_VALUE), steps + 1));
                }
            }

            if (newDp.isEmpty()) {
                return -1;
            }
            dp = newDp;
        }

        // Find the minimum operations among all possible ending values
        int ans = Integer.MAX_VALUE;
        for (int steps : dp.values()) {
            ans = Math.min(ans, steps);
        }
        return ans;
    }

    // Find the first index in arr2[0..m) where arr2[idx] > target
    private int upperBound(int[] arr, int target, int m) {
        int left = 0, right = m;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}