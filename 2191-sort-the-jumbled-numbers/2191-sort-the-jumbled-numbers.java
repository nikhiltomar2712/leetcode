class Solution {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        int[] mapped = new int[n];
        for (int i = 0; i < n; i++) {
            mapped[i] = getMapped(nums[i], mapping);
        }
        Arrays.sort(idx, (a, b) -> mapped[a] != mapped[b] ? mapped[a] - mapped[b] : a - b);
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = nums[idx[i]];
        }
        return res;
    }

    private int getMapped(int num, int[] mapping) {
        String s = Integer.toString(num);
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(mapping[c - '0']);
        }
        return Integer.parseInt(sb.toString());
    }
}