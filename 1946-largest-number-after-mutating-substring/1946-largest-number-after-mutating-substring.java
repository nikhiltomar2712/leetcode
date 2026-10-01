class Solution {
    public String maximumNumber(String num, int[] change) {
        char[] arr = num.toCharArray();
        boolean changed = false;
        for (int i = 0; i < arr.length; i++) {
            int d = arr[i] - '0';
            if (change[d] > d) {
                arr[i] = (char) (change[d] + '0');
                changed = true;
            } else if (change[d] < d && changed) {
                break;
            }
        }
        return new String(arr);
    }
}