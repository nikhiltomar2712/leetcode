public class Solution {
    public double MaxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // Build adjacency list: graph[u] = list of (v, probability)
        var graph = new List<(int node, double prob)>[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new List<(int, double)>();
        }

        for (int i = 0; i < edges.Length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            double p = succProb[i];
            graph[u].Add((v, p));
            graph[v].Add((u, p));
        }

        // maxProb[i] = maximum probability to reach i from start
        double[] maxProb = new double[n];
        maxProb[start] = 1.0;

        // Max-heap: (probability, node)
        var pq = new PriorityQueue<(double prob, int node), double>(
            Comparer<double>.Create((a, b) => b.CompareTo(a))  // max-heap
        );
        pq.Enqueue((1.0, start), 1.0);

        while (pq.Count > 0) {
            var (prob, u) = pq.Dequeue();

            // Early exit if we reached the destination
            if (u == end) return prob;

            // Skip if we already found a better path to u
            if (prob < maxProb[u]) continue;

            foreach (var (v, edgeProb) in graph[u]) {
                double newProb = prob * edgeProb;
                if (newProb > maxProb[v]) {
                    maxProb[v] = newProb;
                    pq.Enqueue((newProb, v), newProb);
                }
            }
        }

        return 0.0; // no path exists
    }
}