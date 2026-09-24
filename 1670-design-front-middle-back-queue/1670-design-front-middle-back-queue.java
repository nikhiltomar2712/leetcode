import java.util.*;

class FrontMiddleBackQueue {
    private Deque<Integer> left;   // front half
    private Deque<Integer> right;  // back half

    public FrontMiddleBackQueue() {
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
    }

    // Rebalance so that left.size() == right.size() or left.size() == right.size() + 1
    private void balance() {
        while (left.size() > right.size() + 1) {
            right.addFirst(left.pollLast());
        }
        while (left.size() < right.size()) {
            left.addLast(right.pollFirst());
        }
    }

    public void pushFront(int val) {
        left.addFirst(val);
        balance();
    }

    public void pushMiddle(int val) {
        // Middle is at the back of left (front-most middle)
        if (left.size() > right.size()) {
            right.addFirst(left.pollLast());
        }
        left.addLast(val);
        balance();
    }

    public void pushBack(int val) {
        right.addLast(val);
        balance();
    }

    public int popFront() {
        if (left.isEmpty() && right.isEmpty()) return -1;
        int val;
        if (!left.isEmpty()) {
            val = left.pollFirst();
        } else {
            val = right.pollFirst();
        }
        balance();
        return val;
    }

    public int popMiddle() {
        if (left.isEmpty() && right.isEmpty()) return -1;
        int val = left.pollLast(); // front-most middle
        balance();
        return val;
    }

    public int popBack() {
        if (left.isEmpty() && right.isEmpty()) return -1;
        int val;
        if (!right.isEmpty()) {
            val = right.pollLast();
        } else {
            val = left.pollLast();
        }
        balance();
        return val;
    }
}