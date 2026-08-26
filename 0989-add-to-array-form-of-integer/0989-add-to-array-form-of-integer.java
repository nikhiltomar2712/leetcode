class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans = new ArrayList<>();

        // Start from the least significant digit
        for (int i = num.length - 1; i >= 0 || k > 0; i--) {
            if (i >= 0) {
                k += num[i];
            }
            ans.add(k % 10);   // current digit
            k /= 10;           // carry
        }

        // Digits were added in reverse order
        Collections.reverse(ans);
        return ans;
    }
}