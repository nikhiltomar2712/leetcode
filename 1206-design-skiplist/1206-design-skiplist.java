import java.util.Random;

class Skiplist {
    private static final int MAX_LEVEL = 32;
    private static final double P = 0.25;
    private final Random random = new Random();
    
    private final Node head = new Node(-1, MAX_LEVEL);
    private int level = 0;   // current maximum level

    private static class Node {
        int val;
        Node[] next;
        
        Node(int val, int level) {
            this.val = val;
            this.next = new Node[level];
        }
    }

    public Skiplist() {
    }

    public boolean search(int target) {
        Node curr = head;
        for (int i = level - 1; i >= 0; i--) {
            curr = findClosest(curr, i, target);
            if (curr.next[i] != null && curr.next[i].val == target) {
                return true;
            }
        }
        return false;
    }

    public void add(int num) {
        int newLevel = randomLevel();
        level = Math.max(level, newLevel);
        
        Node newNode = new Node(num, newLevel);
        Node curr = head;
        
        for (int i = level - 1; i >= 0; i--) {
            curr = findClosest(curr, i, num);
            if (i < newLevel) {
                newNode.next[i] = curr.next[i];
                curr.next[i] = newNode;
            }
        }
    }

    public boolean erase(int num) {
        Node curr = head;
        boolean found = false;
        
        for (int i = level - 1; i >= 0; i--) {
            curr = findClosest(curr, i, num);
            if (curr.next[i] != null && curr.next[i].val == num) {
                curr.next[i] = curr.next[i].next[i];
                found = true;
            }
        }
        
        // Decrease the current level if the highest levels become empty
        while (level > 0 && head.next[level - 1] == null) {
            level--;
        }
        
        return found;
    }

    // Find the rightmost node at the given level whose value is < target
    private Node findClosest(Node curr, int level, int target) {
        while (curr.next[level] != null && curr.next[level].val < target) {
            curr = curr.next[level];
        }
        return curr;
    }

    // Randomly determine the level of a new node
    private int randomLevel() {
        int lvl = 1;
        while (lvl < MAX_LEVEL && random.nextDouble() < P) {
            lvl++;
        }
        return lvl;
    }
}