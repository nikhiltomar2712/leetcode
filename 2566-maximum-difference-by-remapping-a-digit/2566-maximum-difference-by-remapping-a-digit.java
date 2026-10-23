class Solution {
    public int minMaxDifference(int num) {
        String s = Integer.toString(num);
        char maxDigit = ' ';
        for (char c : s.toCharArray()) {
            if (c != '9') {
                maxDigit = c;
                break;
            }
        }
        String maxStr = s;
        if (maxDigit != ' ') {
            maxStr = s.replace(maxDigit, '9');
        }
        char minDigit = s.charAt(0);
        String minStr = s.replace(minDigit, '0');
        return Integer.parseInt(maxStr) - Integer.parseInt(minStr);
    }
}