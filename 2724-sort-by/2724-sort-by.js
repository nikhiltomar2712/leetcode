/**
 * @param {Array} arr
 * @param {Function} fn
 * @return {Array}
 */
var sortBy = function(arr, fn) {
    return [...arr].sort((a, b) => fn(a) - fn(b));
};

/**
 * sortBy([5, 4, 1, 2, 3], (x) => x); // [1, 2, 3, 4, 5]
 * sortBy([{"x": 1}, {"x": 0}, {"x": -1}], (d) => d.x); // [{"x": -1}, {"x": 0}, {"x": 1}]
 */