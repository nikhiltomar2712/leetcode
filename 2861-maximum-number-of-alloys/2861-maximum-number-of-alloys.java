class Solution {
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int res = 0;
        for (int i = 0; i < k; i++) {
            int left = 0, right = 200000000;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                long totalCost = 0;
                boolean ok = true;
                for (int j = 0; j < n; j++) {
                    long need = (long) composition.get(i).get(j) * mid;
                    if (need > stock.get(j)) {
                        totalCost += (need - stock.get(j)) * cost.get(j);
                        if (totalCost > budget) {
                            ok = false;
                            break;
                        }
                    }
                }
                if (ok && totalCost <= budget) {
                    res = Math.max(res, mid);
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return res;
    }
}