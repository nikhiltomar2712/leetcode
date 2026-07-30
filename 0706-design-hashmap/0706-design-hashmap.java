class MyHashMap {
    private static class Node {
        int key, value;
        Node next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private Node[] buckets;
    private static final int CAPACITY = 10000; // Number of buckets
    
    public MyHashMap() {
        buckets = new Node[CAPACITY];
    }
    
    private int hash(int key) {
        return key % CAPACITY;
    }
    
    public void put(int key, int value) {
        int index = hash(key);
        Node head = buckets[index];
        
        // Check if key exists, update if found
        Node current = head;
        while (current != null) {
            if (current.key == key) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        
        // Key not found, add new node at head
        Node newNode = new Node(key, value);
        newNode.next = head;
        buckets[index] = newNode;
    }
    
    public int get(int key) {
        int index = hash(key);
        Node current = buckets[index];
        
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        return -1;
    }
    
    public void remove(int key) {
        int index = hash(key);
        Node current = buckets[index];
        Node prev = null;
        
        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    // Removing head
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }
}