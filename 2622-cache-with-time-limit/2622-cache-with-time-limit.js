var TimeLimitedCache = function() {
  this.cache = new Map(); // key -> { value, expiry }
};

TimeLimitedCache.prototype.set = function(key, value, duration) {
  const now = Date.now();
  const existing = this.cache.get(key);
  const wasValid = existing !== undefined && existing.expiry > now;

  this.cache.set(key, { value, expiry: now + duration });
  return wasValid;
};

TimeLimitedCache.prototype.get = function(key) {
  const entry = this.cache.get(key);
  if (entry === undefined) return -1;
  if (entry.expiry <= Date.now()) {
    this.cache.delete(key); // clean up expired entry
    return -1;
  }
  return entry.value;
};

TimeLimitedCache.prototype.count = function() {
  const now = Date.now();
  let c = 0;
  for (const [key, entry] of this.cache) {
    if (entry.expiry > now) c++;
    else this.cache.delete(key); // optional cleanup
  }
  return c;
};