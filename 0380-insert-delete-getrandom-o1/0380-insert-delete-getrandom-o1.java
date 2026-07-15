import java.util.*;

class RandomizedSet {
    private List<Integer> nums;
    private Map<Integer, Integer> valToIndex;
    private Random rand;

    public RandomizedSet() {
        nums = new ArrayList<>();
        valToIndex = new HashMap<>();
        rand = new Random();
    }
    
    public boolean insert(int val) {
        if (valToIndex.containsKey(val)) {
            return false;
        }
        valToIndex.put(val, nums.size());
        nums.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        if (!valToIndex.containsKey(val)) {
            return false;
        }
        int index = valToIndex.get(val);
        int lastVal = nums.get(nums.size() - 1);
        
        // Swap with last element
        nums.set(index, lastVal);
        valToIndex.put(lastVal, index);
        
        // Remove last element
        nums.remove(nums.size() - 1);
        valToIndex.remove(val);
        
        return true;
    }
    
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}