class Solution {
    public int maxDiff(int num) {
        String s = String.valueOf(num);

        // Build the maximum possible value
        int maxVal = num;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '9') {
                maxVal = replaceAll(s, s.charAt(i), '9');
                break;
            }
        }

        // Build the minimum possible value
        int minVal = num;
        if (s.charAt(0) != '1') {
            minVal = replaceAll(s, s.charAt(0), '1');
        } else {
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) != '0' && s.charAt(i) != '1') {
                    minVal = replaceAll(s, s.charAt(i), '0');
                    break;
                }
            }
        }

        return maxVal - minVal;
    }

    private int replaceAll(String s, char from, char to) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c == from ? to : c);
        }
        return Integer.parseInt(sb.toString());
    }
}