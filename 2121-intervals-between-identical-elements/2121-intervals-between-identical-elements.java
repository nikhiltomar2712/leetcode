class Solution {
    public long[] getDistances(int[] arr) {
        int n = arr.length;
        long[] res = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        for (List<Integer> list : map.values()) {
            int size = list.size();
            long[] prefix = new long[size + 1];
            for (int i = 0; i < size; i++) {
                prefix[i + 1] = prefix[i] + list.get(i);
            }
            for (int i = 0; i < size; i++) {
                long left = (long) list.get(i) * i - prefix[i];
                long right = (prefix[size] - prefix[i + 1]) - (long) list.get(i) * (size - i - 1);
                res[list.get(i)] = left + right;
            }
        }
        return res;
    }
}