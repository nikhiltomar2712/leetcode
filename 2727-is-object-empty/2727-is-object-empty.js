/**
 * @param {Object|Array} obj
 * @return {boolean}
 */
var isEmpty = function(obj) {
    if (Array.isArray(obj)) {
        return obj.length === 0;
    }
    return Object.keys(obj).length === 0;
};

/**
 * isEmpty({"x": 1});   // false
 * isEmpty({});         // true
 * isEmpty([1, 2, 3]);  // false
 * isEmpty([]);         // true
 */