class Solution {
    private rows: number;
    private cols: number;
    private remainingCells: number;
    private swapMap: Map<number, number>;

    constructor(m: number, n: number) {
        this.rows = m;
        this.cols = n;
        this.remainingCells = m * n;
        this.swapMap = new Map<number, number>();
    }

    flip(): number[] {
        const randomIndex = Math.floor(Math.random() * this.remainingCells);
        this.remainingCells--;

        const actualIndex = this.swapMap.get(randomIndex) ?? randomIndex;

        const lastIndexValue = this.swapMap.get(this.remainingCells) ?? this.remainingCells;
        this.swapMap.set(randomIndex, lastIndexValue);

        const row = Math.floor(actualIndex / this.cols);
        const col = actualIndex % this.cols;

        return [row, col];
    }

    reset(): void {
        this.remainingCells = this.rows * this.cols;
        this.swapMap.clear();
    }
}