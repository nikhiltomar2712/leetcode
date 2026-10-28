class Solution {
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        int res = 0;
        int take = Math.min(k, numOnes);
        res += take;
        k -= take;
        take = Math.min(k, numZeros);
        k -= take;
        take = Math.min(k, numNegOnes);
        res -= take;
        return res;
    }
}