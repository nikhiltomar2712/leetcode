function minSteps(n: number): number {
    let steps = 0;
    let d = 2;

    while (n > 1) {
        while (n % d === 0) {
            steps += d;
            n = Math.floor(n / d);
        }
        d++;
    }

    return steps;
}