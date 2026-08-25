class Solution {
    public int oddEvenJumps(int[] arr) {
        int n = arr.length;
        boolean[] odd = new boolean[n];
        boolean[] even = new boolean[n];
        odd[n - 1] = even[n - 1] = true;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(arr[n - 1], n - 1);

        int good = 1; // the last index is always good

        for (int i = n - 2; i >= 0; i--) {
            // Odd jump: smallest value >= arr[i]
            Map.Entry<Integer, Integer> higher = map.ceilingEntry(arr[i]);
            if (higher != null) {
                odd[i] = even[higher.getValue()];
            }

            // Even jump: largest value <= arr[i]
            Map.Entry<Integer, Integer> lower = map.floorEntry(arr[i]);
            if (lower != null) {
                even[i] = odd[lower.getValue()];
            }

            if (odd[i]) {
                good++;
            }

            map.put(arr[i], i); // keep the leftmost (smallest) index for each value
        }

        return good;
    }
}