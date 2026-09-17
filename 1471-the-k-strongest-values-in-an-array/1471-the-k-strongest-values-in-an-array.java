class Solution {
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        int median = arr[(n - 1) / 2];

        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;

        Arrays.sort(indices, (a, b) -> {
            long diffA = Math.abs((long) arr[a] - median);
            long diffB = Math.abs((long) arr[b] - median);
            if (diffA != diffB) return Long.compare(diffB, diffA);
            return Integer.compare(arr[b], arr[a]);
        });

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[indices[i]];
        }

        return result;
    }
}