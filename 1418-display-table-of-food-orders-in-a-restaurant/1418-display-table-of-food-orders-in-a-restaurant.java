import java.util.*;

class Solution {
    public List<List<String>> displayTable(List<List<String>> orders) {
        // All distinct foods
        Set<String> foodSet = new TreeSet<>();
        // All distinct tables
        Set<Integer> tableSet = new TreeSet<>();
        // table -> (food -> count)
        Map<Integer, Map<String, Integer>> tableMap = new HashMap<>();

        for (List<String> order : orders) {
            String customer = order.get(0);
            int table = Integer.parseInt(order.get(1));
            String food = order.get(2);

            foodSet.add(food);
            tableSet.add(table);

            tableMap.putIfAbsent(table, new HashMap<>());
            Map<String, Integer> foodCount = tableMap.get(table);
            foodCount.put(food, foodCount.getOrDefault(food, 0) + 1);
        }

        // Sorted food list for headers
        List<String> foods = new ArrayList<>(foodSet);

        // Build result
        List<List<String>> result = new ArrayList<>();

        // Header row
        List<String> header = new ArrayList<>();
        header.add("Table");
        header.addAll(foods);
        result.add(header);

        // Data rows (tables already sorted by TreeSet)
        for (int table : tableSet) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(table));
            Map<String, Integer> foodCount = tableMap.get(table);
            for (String food : foods) {
                row.add(String.valueOf(foodCount.getOrDefault(food, 0)));
            }
            result.add(row);
        }

        return result;
    }
}