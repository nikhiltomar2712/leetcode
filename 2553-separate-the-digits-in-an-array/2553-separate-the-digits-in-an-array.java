class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            String s = Integer.toString(num);
            for (char c : s.toCharArray()) {
                list.add(c - '0');
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}