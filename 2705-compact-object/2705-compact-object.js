/**
 * @param {Object|Array} obj
 * @return {Object|Array}
 */
var compactObject = function(obj) {
    if (obj === null || typeof obj !== 'object') {
        return obj;
    }

    if (Array.isArray(obj)) {
        const result = [];
        for (const item of obj) {
            const compacted = compactObject(item);
            if (compacted) {
                result.push(compacted);
            }
        }
        return result;
    }

    const result = {};
    for (const key in obj) {
        const compacted = compactObject(obj[key]);
        if (compacted) {
            result[key] = compacted;
        }
    }
    return result;
};