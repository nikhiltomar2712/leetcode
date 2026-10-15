class Solution {
    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Deque<Integer> prev = new ArrayDeque<>();
        Deque<Integer> curr = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!prev.isEmpty() && nums[prev.peek()] < nums[i]) {
                ans[prev.pop()] = nums[i];
            }
            Deque<Integer> temp = new ArrayDeque<>();
            while (!curr.isEmpty() && nums[curr.peek()] < nums[i]) {
                temp.push(curr.pop());
            }
            while (!temp.isEmpty()) {
                prev.push(temp.pop());
            }
            curr.push(i);
        }
        return ans;
    }
}