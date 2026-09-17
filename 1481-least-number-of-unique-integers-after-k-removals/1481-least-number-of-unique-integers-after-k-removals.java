class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        List<Integer> counts = new ArrayList<>(freq.values());
        Collections.sort(counts);

        int unique = counts.size();
        for (int count : counts) {
            if (k >= count) {
                k -= count;
                unique--;
            } else {
                break;
            }
        }

        return unique;
    }
}