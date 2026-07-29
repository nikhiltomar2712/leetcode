class MyHashSet {
public:
    MyHashSet() {
        data.assign(1000001, false);
    }
    
    void add(int key) {
        data[key] = true;
    }
    
    void remove(int key) {
        data[key] = false;
    }
    
    bool contains(int key) {
        return data[key];
    }

private:
    vector<bool> data;
};