public class Solution {
    public int NumIdenticalPairs(int[] nums) {
        int[] count = new int[101]; // values are 1..100
        int ans = 0;

        foreach (int num in nums) {
            ans += count[num];   // number of previous occurrences of num
            count[num]++;
        }

        return ans;
    }
}