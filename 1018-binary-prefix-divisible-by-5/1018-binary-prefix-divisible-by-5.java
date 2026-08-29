class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> answer = new ArrayList<>(nums.length);
        int rem = 0;

        for (int bit : nums) {
            rem = (rem * 2 + bit) % 5;
            answer.add(rem == 0);
        }
        return answer;
    }
}