class Solution {
    public int minImpossibleOR(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if ((num & (num - 1)) == 0) {
                set.add(num);
            }
        }
        int res = 1;
        while (set.contains(res)) {
            res *= 2;
        }
        return res;
    }
}