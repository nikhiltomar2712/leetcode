class Solution {
    public String largestMultipleOfThree(int[] digits) {
        int[] count = new int[10];
        int sum = 0;
        
        for (int d : digits) {
            count[d]++;
            sum += d;
        }
        
        // Digits we prefer to remove first (smallest ones)
        // For remainder 1: remove one mod-1 digit, or two mod-2 digits
        int[] removeForMod1 = {1, 4, 7, 2, 5, 8};
        // For remainder 2: remove one mod-2 digit, or two mod-1 digits
        int[] removeForMod2 = {2, 5, 8, 1, 4, 7};
        
        while (sum % 3 != 0) {
            int[] candidates = (sum % 3 == 1) ? removeForMod1 : removeForMod2;
            boolean removed = false;
            
            for (int d : candidates) {
                if (count[d] > 0) {
                    count[d]--;
                    sum -= d;
                    removed = true;
                    break;
                }
            }
            
            if (!removed) {
                // Impossible to form a multiple of 3
                return "";
            }
        }
        
        // Build the largest number
        StringBuilder sb = new StringBuilder();
        for (int d = 9; d >= 0; d--) {
            while (count[d]-- > 0) {
                sb.append(d);
            }
        }
        
        // Handle leading zeros
        if (sb.length() == 0) return "";
        if (sb.charAt(0) == '0') return "0";
        
        return sb.toString();
    }
}