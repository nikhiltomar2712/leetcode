var flat = function (arr, n) {
  const result = [];

  function flatten(current, depth) {
    for (const item of current) {
      if (Array.isArray(item) && depth < n) {
        flatten(item, depth + 1);
      } else {
        result.push(item);
      }
    }
  }

  flatten(arr, 0);
  return result;
};