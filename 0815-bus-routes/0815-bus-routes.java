class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        // Map each stop → list of bus indices that pass through it
        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToBuses.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
            }
        }

        if (!stopToBuses.containsKey(source) || !stopToBuses.containsKey(target)) {
            return -1;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visitedBuses = new HashSet<>();
        Set<Integer> visitedStops = new HashSet<>();

        queue.offer(source);
        visitedStops.add(source);
        int busesTaken = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            busesTaken++;                       // one more bus ride for this level

            for (int i = 0; i < size; i++) {
                int currStop = queue.poll();

                // Try every bus that stops at the current stop
                for (int bus : stopToBuses.getOrDefault(currStop, Collections.emptyList())) {
                    if (visitedBuses.contains(bus)) continue;
                    visitedBuses.add(bus);

                    // Ride this bus to every stop it reaches
                    for (int nextStop : routes[bus]) {
                        if (nextStop == target) {
                            return busesTaken;
                        }
                        if (visitedStops.add(nextStop)) {
                            queue.offer(nextStop);
                        }
                    }
                }
            }
        }

        return -1;
    }
}