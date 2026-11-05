/**
 * @param {Array<Function>} functions
 * @return {Promise<any>}
 */
var promiseAll = function(functions) {
    return new Promise((resolve, reject) => {
        const n = functions.length;
        const results = new Array(n);
        let count = 0;

        if (n === 0) {
            resolve([]);
            return;
        }

        functions.forEach((fn, i) => {
            // Call fn() immediately — this starts all of them in parallel
            fn()
                .then((value) => {
                    results[i] = value; // preserve original index order
                    count++;
                    if (count === n) {
                        resolve(results);
                    }
                })
                .catch(reject); // first rejection wins
        });
    });
};

/**
 * const functions = [
 *   () => new Promise(res => setTimeout(() => res(5), 200)),
 *   () => new Promise((res, rej) => setTimeout(() => rej("Error"), 100))
 * ];
 * promiseAll(functions).catch(console.log); // "Error"
 */