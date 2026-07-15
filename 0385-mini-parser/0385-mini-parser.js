/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * function NestedInteger() {
 *
 *     Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     @return {boolean}
 *     this.isInteger = function() {
 *         ...
 *     };
 *
 *     Return the single integer that this NestedInteger holds, if it holds a single integer
 *     Return null if this NestedInteger holds a nested list
 *     @return {integer}
 *     this.getInteger = function() {
 *         ...
 *     };
 *
 *     Set this NestedInteger to hold a single integer equal to value.
 *     @return {void}
 *     this.setInteger = function(value) {
 *         ...
 *     };
 *
 *     Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
 *     @return {void}
 *     this.add = function(elem) {
 *         ...
 *     };
 *
 *     Return the nested list that this NestedInteger holds, if it holds a nested list
 *     Return null if this NestedInteger holds a single integer
 *     @return {NestedInteger[]}
 *     this.getList = function() {
 *         ...
 *     };
 * };
 */
/**
 * @param {string} s
 * @return {NestedInteger}
 */
var deserialize = function(s) {
    // If the string starts with '[', it's a list; otherwise, it's a single integer.
    if (s[0] !== '[') {
        // Parse the single integer.
        const ni = new NestedInteger();
        ni.setInteger(parseInt(s));
        return ni;
    }

    // Stack to keep track of the current NestedInteger objects.
    const stack = [];
    // Current NestedInteger being built (represents the list).
    let current = null;
    // Variable to accumulate digits for a number.
    let num = 0;
    // Sign of the current number.
    let sign = 1;
    // Flag to indicate if we are currently reading a number.
    let isNumber = false;

    for (let i = 0; i < s.length; i++) {
        const ch = s[i];

        if (ch === '[') {
            // Start of a new list.
            const newNi = new NestedInteger();
            if (current !== null) {
                // If there is a current list, push it to stack and set current to the new list.
                stack.push(current);
            }
            current = newNi;
        } else if (ch === ']') {
            // End of the current list.
            if (isNumber) {
                // If we were reading a number, add it to the current list.
                current.add(new NestedInteger(sign * num));
                num = 0;
                sign = 1;
                isNumber = false;
            }
            if (stack.length > 0) {
                // Pop the parent list from the stack and add the completed current list to it.
                const parent = stack.pop();
                parent.add(current);
                current = parent;
            }
        } else if (ch === ',') {
            // Separator between elements.
            if (isNumber) {
                // If we were reading a number, add it to the current list.
                current.add(new NestedInteger(sign * num));
                num = 0;
                sign = 1;
                isNumber = false;
            }
        } else if (ch === '-') {
            // Negative sign.
            sign = -1;
        } else {
            // Digit character: build the number.
            if (!isNumber) {
                isNumber = true;
                num = 0;
            }
            num = num * 10 + parseInt(ch);
        }
    }

    // If the input was a single integer, it would have been handled earlier.
    // For a list, the final 'current' should be the root NestedInteger.
    // However, after processing the last ']', current is set to the root.
    // If s was "[...]", after the loop current is the root NestedInteger.
    // But for the case of a single integer without brackets, we returned earlier.
    // For a list, we need to return the root, which is the last 'current' after all brackets are processed.
    // But due to how we handle ']', when we finish the outermost list, stack is empty and current is the root.
    // We need to return current, but careful: after the last ']', we might have added the root to a parent if stack not empty.
    // Since s is valid, after processing the last ']', stack is empty and current is the root NestedInteger.
    // However, for s = "[123]", the flow:
    // i=0 '[' -> newNi, current = newNi
    // i=1 '1' -> isNumber true, num=1
    // i=2 '2' -> num=12
    // i=3 '3' -> num=123
    // i=4 ']' -> isNumber true, add 123 to current, stack empty, no parent, current remains the list containing 123.
    // Return current.
    // For s = "[123,[456,[789]]]", similar logic works.
    return current;
};