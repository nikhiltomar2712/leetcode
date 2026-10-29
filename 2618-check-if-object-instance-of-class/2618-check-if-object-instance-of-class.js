var checkIfInstanceOf = function(obj, classFunction) {
  if (obj === null || obj === undefined) return false;
  if (typeof classFunction !== "function") return false;

  // Box primitives so we have a prototype to walk
  let proto = Object.getPrototypeOf(
    typeof obj === "object" || typeof obj === "function" ? obj : Object(obj)
  );

  while (proto !== null) {
    if (proto === classFunction.prototype) return true;
    proto = Object.getPrototypeOf(proto);
  }
  return false;
};