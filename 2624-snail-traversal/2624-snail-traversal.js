Array.prototype.snail = function(rowsCount, colsCount) {
  if (rowsCount * colsCount !== this.length) return [];

  const grid = Array.from({ length: rowsCount }, () => Array(colsCount).fill(0));
  const nums = [...this];

  let i = 0;
  // The order of directions: down the right column, up the left column,
  // down the next right column, up the next left column, ...
  for (let col = 0; col < colsCount; col++) {
    if (col % 2 === 0) {
      // top -> bottom in this column
      for (let row = 0; row < rowsCount; row++) {
        grid[row][col] = nums[i++];
      }
    } else {
      // bottom -> top in this column
      for (let row = rowsCount - 1; row >= 0; row--) {
        grid[row][col] = nums[i++];
      }
    }
  }
  return grid;
};