class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> result = new ArrayList<>();
        int m = l.length;

        for (int i = 0; i < m; i++) {
            int left = l[i], right = r[i];
            int[] sub = Arrays.copyOfRange(nums, left, right + 1);
            Arrays.sort(sub);
            result.add(isArithmetic(sub));
        }

        return result;
    }

    private boolean isArithmetic(int[] arr) {
        if (arr.length <= 2) return true;

        int diff = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != diff) return false;
        }
        return true;
    }
}