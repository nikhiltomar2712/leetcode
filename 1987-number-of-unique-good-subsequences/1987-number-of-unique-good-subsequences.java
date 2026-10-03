class Solution {
    public int numberOfUniqueGoodSubsequences(String binary) {
        int mod = 1_000_000_007;
        long ends0 = 0, ends1 = 0;
        boolean hasZero = false;
        for (char c : binary.toCharArray()) {
            if (c == '1') {
                ends1 = (ends1 + ends0 + 1) % mod;
            } else {
                ends0 = (ends0 + ends1) % mod;
                hasZero = true;
            }
        }
        return (int) ((ends0 + ends1 + (hasZero ? 1 : 0)) % mod);
    }
}