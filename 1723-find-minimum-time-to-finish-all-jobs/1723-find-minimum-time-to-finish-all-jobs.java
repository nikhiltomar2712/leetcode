class Solution {
    private int[] jobs;
    private int[] workers;
    private int k;
    private int best;

    public int minimumTimeRequired(int[] jobs, int k) {
        this.jobs = jobs;
        this.k = k;
        this.workers = new int[k];
        this.best = Integer.MAX_VALUE;

        // Sort jobs descending to improve pruning
        Arrays.sort(jobs);
        int n = jobs.length;
        for (int i = 0; i < n / 2; i++) {
            int tmp = jobs[i];
            jobs[i] = jobs[n - 1 - i];
            jobs[n - 1 - i] = tmp;
        }

        backtrack(0, 0, 0);
        return best;
    }

    private void backtrack(int index, int currentMax, int usedWorkers) {
        if (currentMax >= best) return;

        if (index == jobs.length) {
            best = Math.min(best, currentMax);
            return;
        }

        // Prune: if remaining workers can't improve, or usedWorkers exceeds k
        if (usedWorkers > k) return;

        // Try assigning job to each worker
        Set<Integer> tried = new HashSet<>();
        for (int i = 0; i < k; i++) {
            if (tried.contains(workers[i])) continue;
            tried.add(workers[i]);

            workers[i] += jobs[index];
            backtrack(index + 1, Math.max(currentMax, workers[i]),
                      usedWorkers + (workers[i] == jobs[index] ? 1 : 0));
            workers[i] -= jobs[index];

            // If this worker was idle, no need to try other idle workers
            if (workers[i] == 0) break;
        }
    }
}