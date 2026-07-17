#include <vector>
#include <unordered_map>
#include <random>

class RandomizedSet {
private:
    std::vector<int> nums;                      // Stores the actual values
    std::unordered_map<int, int> valToIndex;    // Maps value -> its index in nums
    std::mt19937 rng;                           // Random number generator

public:
    RandomizedSet() : rng(std::random_device{}()) {
        // Constructor initializes the random number generator
    }
    
    bool insert(int val) {
        // If value already exists, insertion fails
        if (valToIndex.find(val) != valToIndex.end()) {
            return false;
        }
        
        // Add value to the end of the array
        nums.push_back(val);
        // Store its index in the hash map
        valToIndex[val] = nums.size() - 1;
        return true;
    }
    
    bool remove(int val) {
        // If value doesn't exist, removal fails
        if (valToIndex.find(val) == valToIndex.end()) {
            return false;
        }
        
        // Get the index of the value to remove
        int indexToRemove = valToIndex[val];
        int lastElement = nums.back();
        
        // Swap the element to remove with the last element
        nums[indexToRemove] = lastElement;
        // Update the hash map for the moved element
        valToIndex[lastElement] = indexToRemove;
        
        // Remove the last element (which is now the value we want to delete)
        nums.pop_back();
        // Erase the value from the hash map
        valToIndex.erase(val);
        
        return true;
    }
    
    int getRandom() {
        // Generate a random index and return the value at that index
        std::uniform_int_distribution<int> dist(0, nums.size() - 1);
        int randomIndex = dist(rng);
        return nums[randomIndex];
    }
};

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet* obj = new RandomizedSet();
 * bool param_1 = obj->insert(val);
 * bool param_2 = obj->remove(val);
 * int param_3 = obj->getRandom();
 */