class Solution {
    public String thousandSeparator(int n) {
        String s = Integer.toString(n);
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (count > 0 && count % 3 == 0) {
                sb.append('.');
            }
            sb.append(s.charAt(i));
            count++;
        }

        return sb.reverse().toString();
    }
}