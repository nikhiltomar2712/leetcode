class Solution {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int i = arr1.length - 1;
        int j = arr2.length - 1;
        int carry = 0;
        Deque<Integer> ans = new ArrayDeque<>();

        while (i >= 0 || j >= 0 || carry != 0) {
            if (i >= 0) {
                carry += arr1[i--];
            }
            if (j >= 0) {
                carry += arr2[j--];
            }
            // Current bit is the least significant bit of carry
            ans.addFirst(carry & 1);
            // Carry for next (higher) position in base -2
            carry = -(carry >> 1);
        }

        // Remove leading zeros (keep at least one digit)
        while (ans.size() > 1 && ans.peekFirst() == 0) {
            ans.pollFirst();
        }

        // Convert to array
        int[] result = new int[ans.size()];
        int idx = 0;
        for (int bit : ans) {
            result[idx++] = bit;
        }
        return result;
    }
}