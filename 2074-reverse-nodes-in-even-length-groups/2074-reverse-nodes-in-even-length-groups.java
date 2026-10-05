class Solution {
    public ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        int groupSize = 1;
        while (prev.next != null) {
            ListNode cur = prev.next;
            int count = 0;
            ListNode temp = cur;
            while (temp != null && count < groupSize) {
                temp = temp.next;
                count++;
            }
            if (count % 2 == 0) {
                ListNode reversed = reverse(cur, count);
                prev.next = reversed;
                for (int i = 0; i < count; i++) {
                    prev = prev.next;
                }
            } else {
                for (int i = 0; i < count; i++) {
                    prev = prev.next;
                }
            }
            groupSize++;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head, int k) {
        ListNode prev = null, cur = head;
        for (int i = 0; i < k; i++) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head.next = cur;
        return prev;
    }
}