class Solution {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] res = new int[m][n];
        for (int[] row : res) Arrays.fill(row, -1);
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        ListNode cur = head;
        while (cur != null && top <= bottom && left <= right) {
            for (int j = left; j <= right && cur != null; j++) {
                res[top][j] = cur.val;
                cur = cur.next;
            }
            top++;
            for (int i = top; i <= bottom && cur != null; i++) {
                res[i][right] = cur.val;
                cur = cur.next;
            }
            right--;
            for (int j = right; j >= left && cur != null; j--) {
                res[bottom][j] = cur.val;
                cur = cur.next;
            }
            bottom--;
            for (int i = bottom; i >= top && cur != null; i--) {
                res[i][left] = cur.val;
                cur = cur.next;
            }
            left++;
        }
        return res;
    }
}