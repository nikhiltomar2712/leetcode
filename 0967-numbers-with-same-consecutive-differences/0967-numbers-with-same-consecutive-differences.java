class Solution {
    private List<Integer> ans = new ArrayList<>();
    private int k;
    private int boundary;

    public int[] numsSameConsecDiff(int n, int k) {
        this.k = k;
        this.boundary = (int) Math.pow(10, n - 1);

        // First digit cannot be 0
        for (int i = 1; i <= 9; i++) {
            dfs(i);
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private void dfs(int num) {
        if (num >= boundary) {          // we have built an n-digit number
            ans.add(num);
            return;
        }

        int last = num % 10;

        // next digit = last + k
        if (last + k <= 9) {
            dfs(num * 10 + last + k);
        }

        // next digit = last - k  (skip when k == 0 to avoid duplicates)
        if (k != 0 && last - k >= 0) {
            dfs(num * 10 + last - k);
        }
    }
}