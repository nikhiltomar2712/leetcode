class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        // Map from group size → list of people who need that size
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < groupSizes.length; i++) {
            int size = groupSizes[i];
            map.computeIfAbsent(size, k -> new ArrayList<>()).add(i);
        }
        
        List<List<Integer>> result = new ArrayList<>();
        
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int size = entry.getKey();
            List<Integer> people = entry.getValue();
            
            // Split the people into groups of exactly 'size'
            for (int i = 0; i < people.size(); i += size) {
                result.add(people.subList(i, i + size));
            }
        }
        
        return result;
    }
}
