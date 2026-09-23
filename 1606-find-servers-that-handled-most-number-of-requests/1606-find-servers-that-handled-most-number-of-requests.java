import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) available.add(i);

        // min-heap: [freeTime, serverIndex]
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[] count = new int[k];
        int maxCount = 0;

        for (int i = 0; i < arrival.length; i++) {
            int time = arrival[i];

            // Release all servers whose load has finished by now
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                available.add(busy.poll()[1]);
            }

            if (available.isEmpty()) continue; // request dropped

            int target = i % k;
            Integer server = available.ceiling(target);
            if (server == null) server = available.first(); // wrap around

            available.remove(server);
            busy.add(new int[]{time + load[i], server});
            count[server]++;
            maxCount = Math.max(maxCount, count[server]);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxCount) result.add(i);
        }
        return result;
    }
}