/**
 * @param {Function} fn
 * @param {Array} args
 * @param {number} t
 * @return {Function}
 */
var cancellable = function(fn, args, t) {
    const timerId = setTimeout(() => fn(...args), t);

    return function cancelFn() {
        clearTimeout(timerId);
    };
};

/**
 * const cancelFn = cancellable((x) => x * 5, [2], 20);
 * setTimeout(cancelFn, 50);   // fn never runs because cancel happens at 50ms > 20ms? 
 *                             // (here 50 > 20, so fn DOES run at 20ms)
 */