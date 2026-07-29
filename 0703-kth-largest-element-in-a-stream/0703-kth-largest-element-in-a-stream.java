import java.util.PriorityQueue;

class KthLargest {
    private PriorityQueue<Integer> minHeap; // stores the k largest elements
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>(k); // Min-heap by default
        
        // Add all initial elements to the heap
        for (int num : nums) {
            add(num); // reusing add logic to maintain size k
        }
    }
    
    public int add(int val) {
        // Add the new value to the heap
        minHeap.offer(val);
        
        // If heap size exceeds k, remove the smallest element
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        
        // The root of the min-heap is the kth largest element
        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */