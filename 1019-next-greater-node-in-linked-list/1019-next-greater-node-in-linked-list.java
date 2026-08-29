class Solution {
    public int[] nextLargerNodes(ListNode head) {
        // Convert linked list to ArrayList for easy indexing
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int n = list.size();
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(); // stores indices

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && list.get(i) > list.get(stack.peek())) {
                answer[stack.pop()] = list.get(i);
            }
            stack.push(i);
        }
        // Remaining indices already have answer[i] = 0
        return answer;
    }
}