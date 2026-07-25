class Solution {
    public int findLongestChain(int[][] pairs) {
        // Sort pairs by the ending value (right)
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 0;
        int currentEnd = Integer.MIN_VALUE;

        for (int[] pair : pairs) {
            if (pair[0] > currentEnd) {
                // Can extend the chain
                count++;
                currentEnd = pair[1];
            }
        }

        return count;
    }
}