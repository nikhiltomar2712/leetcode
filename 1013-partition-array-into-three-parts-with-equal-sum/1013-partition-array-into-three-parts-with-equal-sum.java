class Solution {
    public boolean canThreePartsEqualSum(int[] arr) {
        int total = 0;
        for (int num : arr) total += num;
        
        if (total % 3 != 0) return false;
        
        int target = total / 3;
        int current = 0;
        int count = 0;
        
        for (int i = 0; i < arr.length; i++) {
            current += arr[i];
            if (current == target) {
                count++;
                current = 0;
                // Once we have 2 parts and still have elements left,
                // the remaining elements automatically form the 3rd part
                if (count == 2 && i < arr.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}