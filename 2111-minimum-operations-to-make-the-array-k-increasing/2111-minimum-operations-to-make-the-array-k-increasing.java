class Solution {
    public int kIncreasing(int[] arr, int k) {
        int n = arr.length;
        int res = 0;
        for (int i = 0; i < k; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = i; j < n; j += k) {
                list.add(arr[j]);
            }
            res += list.size() - longestNonDecreasing(list);
        }
        return res;
    }

    private int longestNonDecreasing(List<Integer> list) {
        List<Integer> tails = new ArrayList<>();
        for (int x : list) {
            int lo = 0, hi = tails.size();
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (tails.get(mid) <= x) lo = mid + 1;
                else hi = mid;
            }
            if (lo == tails.size()) tails.add(x);
            else tails.set(lo, x);
        }
        return tails.size();
    }
}