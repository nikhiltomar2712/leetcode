class Solution {
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        Map<Integer, Integer> even = new HashMap<>();
        Map<Integer, Integer> odd = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) even.merge(nums[i], 1, Integer::sum);
            else odd.merge(nums[i], 1, Integer::sum);
        }

        int evenMax1 = 0, evenMax2 = 0, evenVal = -1;
        for (Map.Entry<Integer, Integer> e : even.entrySet()) {
            if (e.getValue() > evenMax1) {
                evenMax2 = evenMax1;
                evenMax1 = e.getValue();
                evenVal = e.getKey();
            } else if (e.getValue() > evenMax2) {
                evenMax2 = e.getValue();
            }
        }

        int oddMax1 = 0, oddMax2 = 0, oddVal = -1;
        for (Map.Entry<Integer, Integer> e : odd.entrySet()) {
            if (e.getValue() > oddMax1) {
                oddMax2 = oddMax1;
                oddMax1 = e.getValue();
                oddVal = e.getKey();
            } else if (e.getValue() > oddMax2) {
                oddMax2 = e.getValue();
            }
        }

        int evenCount = (n + 1) / 2;
        int oddCount = n / 2;

        if (evenVal != oddVal) {
            return (evenCount - evenMax1) + (oddCount - oddMax1);
        } else {
            return Math.min(
                (evenCount - evenMax1) + (oddCount - oddMax2),
                (evenCount - evenMax2) + (oddCount - oddMax1)
            );
        }
    }
}