/**
 * @param {number} capacity
 */
var LRUCache = function(capacity) {
    this.capacity = capacity;
    this.cache = new Map();
};

LRUCache.prototype.get = function(key) {
    if (!this.cache.has(key)) return -1;
    
    const value = this.cache.get(key);
    // Move to end (most recent)
    this.cache.delete(key);
    this.cache.set(key, value);
    return value;
};

LRUCache.prototype.put = function(key, value) {
    // Delete if exists to update position
    if (this.cache.has(key)) {
        this.cache.delete(key);
    }
    
    // Add to end (most recent)
    this.cache.set(key, value);
    
    // Remove least recently used if over capacity
    if (this.cache.size > this.capacity) {
        const lruKey = this.cache.keys().next().value;
        this.cache.delete(lruKey);
    }
};