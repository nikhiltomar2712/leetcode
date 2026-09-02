class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        // Pair value with its label
        int[][] items = new int[n][2];
        for (int i = 0; i < n; i++) {
            items[i][0] = values[i];
            items[i][1] = labels[i];
        }

        // Sort by value descending
        Arrays.sort(items, (a, b) -> b[0] - a[0]);

        // Track how many times each label has been used
        Map<Integer, Integer> labelCount = new HashMap<>();
        int sum = 0;
        int taken = 0;

        for (int[] item : items) {
            if (taken == numWanted) break;

            int val = item[0];
            int lab = item[1];
            int used = labelCount.getOrDefault(lab, 0);

            if (used < useLimit) {
                sum += val;
                labelCount.put(lab, used + 1);
                taken++;
            }
        }

        return sum;
    }
}