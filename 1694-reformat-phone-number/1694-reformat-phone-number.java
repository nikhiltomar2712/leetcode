class Solution {
    public String reformatNumber(String number) {
        // Step 1: Extract all digits
        StringBuilder digits = new StringBuilder();
        for (char c : number.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.append(c);
            }
        }
        
        // Step 2: Group into blocks
        StringBuilder result = new StringBuilder();
        int n = digits.length();
        int i = 0;
        
        // Process in blocks of 3 while more than 4 digits remain
        while (n - i > 4) {
            result.append(digits.substring(i, i + 3)).append('-');
            i += 3;
        }
        
        // Handle the final 2-4 digits
        int remaining = n - i;
        if (remaining == 4) {
            result.append(digits.substring(i, i + 2)).append('-');
            result.append(digits.substring(i + 2, i + 4));
        } else {
            // remaining == 2 or 3
            result.append(digits.substring(i, i + remaining));
        }
        
        return result.toString();
    }
}