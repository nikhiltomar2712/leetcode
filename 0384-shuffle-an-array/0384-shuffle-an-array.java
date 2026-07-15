class Solution {
    private int[] nums;
    private int[] original;
    private Random rand;

    public Solution(int[] nums) {
        this.nums = nums;
        this.original = nums.clone();
        this.rand = new Random();
    }
    
    public int[] reset() {
        nums = original.clone();
        return nums;
    }
    
    public int[] shuffle() {
        // Fisher-Yates shuffle
        for (int i = nums.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            // Swap nums[i] and nums[j]
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }
}