class Solution {
    public int splitNum(int num) {
        char[] s = String.valueOf(num).toCharArray();
        Arrays.sort(s);
        int a = 0, b = 0;
        for (int i = 0; i < s.length; i++) {
            if (i % 2 == 0) a = a * 10 + (s[i] - '0');
            else b = b * 10 + (s[i] - '0');
        }
        return a + b;
    }
}