class DinnerPlates {
    private int capacity;
    private List<Deque<Integer>> stacks;
    private TreeSet<Integer> notFull;   // indices of stacks that still have space

    public DinnerPlates(int capacity) {
        this.capacity = capacity;
        this.stacks = new ArrayList<>();
        this.notFull = new TreeSet<>();
    }

    public void push(int val) {
        if (notFull.isEmpty()) {
            // No available non-full stack → create a new one
            stacks.add(new ArrayDeque<>());
            stacks.get(stacks.size() - 1).push(val);
            if (capacity > 1) {
                notFull.add(stacks.size() - 1);
            }
        } else {
            // Push into the leftmost non-full stack
            int index = notFull.first();
            stacks.get(index).push(val);
            if (stacks.get(index).size() == capacity) {
                notFull.pollFirst();   // it is now full
            }
        }
    }

    public int pop() {
        return popAtStack(stacks.size() - 1);
    }

    public int popAtStack(int index) {
        if (index < 0 || index >= stacks.size() || stacks.get(index).isEmpty()) {
            return -1;
        }

        int val = stacks.get(index).pop();

        if (index == stacks.size() - 1 && stacks.get(index).isEmpty()) {
            // Clean up trailing empty stacks
            while (!stacks.isEmpty() && stacks.get(stacks.size() - 1).isEmpty()) {
                notFull.remove(stacks.size() - 1);
                stacks.remove(stacks.size() - 1);
            }
        } else {
            // This stack now has space
            notFull.add(index);
        }

        return val;
    }
}