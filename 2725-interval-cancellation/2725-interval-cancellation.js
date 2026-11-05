/**
 * @param {Function} fn
 * @param {Array} args
 * @param {number} t
 * @return {Function}
 */
var cancellable = function(fn, args, t) {
    fn(...args);                                   // immediate first call
    const timerId = setInterval(() => fn(...args), t); // then every t ms

    return function cancelFn() {
        clearInterval(timerId);
    };
};

/**
 * const cancelFn = cancellable((x) => x * 2, [4], 35);
 * setTimeout(cancelFn, 190);
 */