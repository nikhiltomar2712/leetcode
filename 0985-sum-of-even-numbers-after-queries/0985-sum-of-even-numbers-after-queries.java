class Solution {
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        // Calculate initial sum of even numbers
        int evenSum = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                evenSum += num;
            }
        }

        int[] answer = new int[queries.length];
        int idx = 0;

        for (int[] query : queries) {
            int val = query[0];
            int index = query[1];

            // If current value is even, remove it from the sum
            if (nums[index] % 2 == 0) {
                evenSum -= nums[index];
            }

            // Apply the query
            nums[index] += val;

            // If the new value is even, add it to the sum
            if (nums[index] % 2 == 0) {
                evenSum += nums[index];
            }

            answer[idx++] = evenSum;
        }

        return answer;
    }
}