import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // Step 1: Assign unique group IDs to items that have no group
        int groupId = m;
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = groupId++;
            }
        }

        // Step 2: Build graphs and indegrees
        // Item graph (within the same group)
        List<Integer>[] itemGraph = new ArrayList[n];
        int[] itemIndegree = new int[n];
        // Group graph
        List<Integer>[] groupGraph = new ArrayList[groupId];
        int[] groupIndegree = new int[groupId];

        for (int i = 0; i < n; i++) {
            itemGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < groupId; i++) {
            groupGraph[i] = new ArrayList<>();
        }

        // Group items by their group
        List<Integer>[] groupItems = new ArrayList[groupId];
        for (int i = 0; i < groupId; i++) {
            groupItems[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            groupItems[group[i]].add(i);
        }

        // Build edges
        for (int curr = 0; curr < n; curr++) {
            for (int prev : beforeItems.get(curr)) {
                if (group[curr] == group[prev]) {
                    // Same group → item-level dependency
                    itemGraph[prev].add(curr);
                    itemIndegree[curr]++;
                } else {
                    // Different groups → group-level dependency
                    groupGraph[group[prev]].add(group[curr]);
                    groupIndegree[group[curr]]++;
                }
            }
        }

        // Step 3: Topological sort of groups
        List<Integer> groupOrder = topologicalSort(groupGraph, groupIndegree, groupId);
        if (groupOrder.isEmpty()) {
            return new int[0];
        }

        // Step 4: Topological sort of items inside each group
        List<Integer> result = new ArrayList<>();
        for (int g : groupOrder) {
            List<Integer> itemsInGroup = groupItems[g];
            List<Integer> itemOrder = topologicalSort(itemGraph, itemIndegree, itemsInGroup);
            if (itemOrder.size() != itemsInGroup.size()) {
                return new int[0]; // cycle inside the group
            }
            result.addAll(itemOrder);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private List<Integer> topologicalSort(List<Integer>[] graph, int[] indegree, int totalNodes) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < totalNodes; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order.add(u);
            for (int v : graph[u]) {
                if (--indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return order.size() == totalNodes ? order : new ArrayList<>();
    }

    // Overloaded version that only considers a subset of nodes
    private List<Integer> topologicalSort(List<Integer>[] graph, int[] indegree, List<Integer> nodes) {
        Queue<Integer> queue = new LinkedList<>();
        for (int node : nodes) {
            if (indegree[node] == 0) {
                queue.offer(node);
            }
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order.add(u);
            for (int v : graph[u]) {
                if (--indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return order;
    }
}