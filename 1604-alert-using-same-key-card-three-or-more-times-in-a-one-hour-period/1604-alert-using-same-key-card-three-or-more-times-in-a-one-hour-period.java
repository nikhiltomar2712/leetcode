import java.util.*;

class Solution {
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < keyName.length; i++) {
            String[] parts = keyTime[i].split(":");
            int minutes = Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
            map.computeIfAbsent(keyName[i], k -> new ArrayList<>()).add(minutes);
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> e : map.entrySet()) {
            List<Integer> times = e.getValue();
            Collections.sort(times);

            for (int i = 2; i < times.size(); i++) {
                if (times.get(i) - times.get(i - 2) <= 60) {
                    result.add(e.getKey());
                    break; // one alert per employee
                }
            }
        }

        Collections.sort(result);
        return result;
    }
}