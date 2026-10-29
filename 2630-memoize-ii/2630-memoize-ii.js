function memoize(fn) {
  const root = new Map();

  return function(...args) {
    let node = root;
    for (const arg of args) {
      if (!node.has(arg)) node.set(arg, new Map());
      node = node.get(arg);
    }
    // The leaf stores the result under a special marker key.
    if (node.has("__result__")) return node.get("__result__");
    const result = fn(...args);
    node.set("__result__", result);
    return result;
  };
}